package org.zalando.intellij.swagger.completion.traversal;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.yaml.psi.YAMLFile;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLSequence;
import org.jetbrains.yaml.psi.YAMLSequenceItem;
import org.jetbrains.yaml.psi.YAMLValue;
import org.zalando.intellij.swagger.completion.level.field.Field;
import org.zalando.intellij.swagger.completion.level.inserthandler.YamlInsertHandler;
import org.zalando.intellij.swagger.completion.style.CompletionStyle;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class YamlTraversal implements Traversal {

    @Override
    public boolean isRoot(final PsiElement psiElement) {
        return psiElement.getParent().getParent() instanceof YAMLFile ||
                psiElement.getParent().getParent().getParent().getParent() instanceof YAMLFile;
    }

    @Override
    public boolean isInfo(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("info"))
                .isPresent();
    }

    @Override
    public boolean isContact(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("contact"))
                .isPresent();
    }

    @Override
    public boolean isLicense(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("license"))
                .isPresent();
    }

    @Override
    public boolean isPath(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 2))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("paths"))
                .isPresent();
    }

    @Override
    public boolean isOperation(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 3))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("paths"))
                .isPresent();
    }

    @Override
    public boolean isExternalDocs(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("externalDocs"))
                .isPresent();
    }

    @Override
    public boolean isParameters(final PsiElement psiElement) {
        final boolean insideParameters = Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("parameters"))
                .isPresent();
        return insideParameters && hasSequenceItemAsParent(psiElement);
    }

    private boolean hasSequenceItemAsParent(final PsiElement psiElement) {
        return psiElement != null &&
                (psiElement instanceof YAMLSequenceItem || hasSequenceItemAsParent(psiElement.getParent()));
    }

    @Override
    public boolean isItems(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("items"))
                .isPresent();
    }

    @Override
    public boolean isResponses(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("responses"))
                .isPresent();
    }

    @Override
    public boolean isResponse(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 2))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("responses"))
                .isPresent();
    }

    @Override
    public boolean isHeader(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 2))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("headers"))
                .isPresent();
    }

    @Override
    public boolean isTag(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("tags"))
                .isPresent();
    }

    @Override
    public boolean isSecurityDefinition(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 2))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("securityDefinitions"))
                .isPresent();
    }

    @Override
    public boolean isSchema(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("schema"))
                .isPresent();
    }

    @Override
    public boolean isXml(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("xml"))
                .isPresent();
    }

    @Override
    public boolean isDefinitions(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 2))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("definitions"))
                .isPresent();
    }

    @Override
    public boolean isParameterDefinition(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 2))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("parameters"))
                .isPresent() && !isInsideParametersArray(psiElement);
    }

    @Override
    public boolean isMimeValue(final PsiElement psiElement) {
        final boolean insideConsumerOrProduces = Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("consumes") || name.equals("produces"))
                .isPresent();
        return insideConsumerOrProduces && hasSequenceItemAsParent(psiElement);
    }

    private YAMLKeyValue getNthYamlKeyValue(final PsiElement psiElement, int nth) {
        if (psiElement == null) {
            return null;
        } else if (psiElement instanceof YAMLKeyValue) {
            if (nth == 1) {
                return (YAMLKeyValue) psiElement;
            } else {
                nth--;
            }
        }

        return getNthYamlKeyValue(psiElement.getParent(), nth);
    }

    private <T extends PsiElement> Optional<T> getNthOfType(final PsiElement psiElement, int nth, Class<T> targetType) {
        if (psiElement == null) {
            return Optional.empty();
        } else if (targetType.isAssignableFrom(psiElement.getClass())) {
            if (nth == 1) {
                return Optional.of(targetType.cast(psiElement));
            } else {
                nth--;
            }
        }
        return getNthOfType(psiElement.getParent(), nth, targetType);
    }

    @Override
    public boolean shouldQuote(final PsiElement psiElement) {
        return false;
    }

    @Override
    public CompletionStyle.QuoteStyle getQuoteStyle() {
        return CompletionStyle.QuoteStyle.SINGLE;
    }

    @Override
    public boolean isSchemesValue(final PsiElement psiElement) {
        final boolean insideSchemes = Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("schemes"))
                .isPresent();
        return insideSchemes && hasSequenceItemAsParent(psiElement);
    }

    @Override
    public boolean isDefinitionRefValue(final PsiElement psiElement) {
        return isRefValue(psiElement) && !isInsideParametersArray(psiElement);
    }

    @Override
    public boolean isParameterRefValue(final PsiElement psiElement) {
        return isRefValue(psiElement) && isInsideParametersArray(psiElement);
    }

    private boolean isRefValue(final PsiElement psiElement) {
        return Optional.ofNullable(getNthYamlKeyValue(psiElement, 1))
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("$ref"))
                .isPresent();
    }

    private boolean isInsideParametersArray(final PsiElement psiElement) {
        return getNthOfType(psiElement, 1, YAMLSequence.class)
                .map(YAMLSequence::getParent)
                .filter(el -> el instanceof YAMLKeyValue)
                .map(YAMLKeyValue.class::cast)
                .map(YAMLKeyValue::getName)
                .filter(name -> name.equals("parameters"))
                .isPresent();
    }

    @Override
    public List<PsiElement> getChildrenOf(final String propertyName, final PsiFile psiFile) {
        return getRootChildren(psiFile).stream()
                .filter(child -> child instanceof YAMLKeyValue)
                .map(YAMLKeyValue.class::cast)
                .filter(yamlKeyValue -> propertyName.equals(yamlKeyValue.getName()))
                .findAny()
                .map(YAMLKeyValue::getValue)
                .map(YAMLValue::getYAMLElements)
                .map(c -> c.stream().map(PsiElement.class::cast).collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
    }

    @Override
    public List<String> getKeyNamesOf(final String propertyName, final PsiFile containingFile) {
        return getChildrenOf(propertyName, containingFile).stream()
                .filter(el -> el instanceof YAMLKeyValue)
                .map(YAMLKeyValue.class::cast)
                .map(YAMLKeyValue::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUniqueKey(final String keyName, final PsiElement psiElement) {
        return Optional.ofNullable(psiElement.getParent())
                .map(PsiElement::getParent)
                .map(el -> Arrays.asList(el.getChildren()))
                .map(children -> children.stream().filter(c -> c instanceof YAMLKeyValue))
                .map(childrenStream -> childrenStream.map(YAMLKeyValue.class::cast))
                .map(childrenStream -> childrenStream.noneMatch(yamlKeyValue -> keyName.equals(yamlKeyValue.getName())))
                .orElse(true);
    }

    @Override
    public InsertHandler<LookupElement> createInsertHandler(final Field field) {
        return new YamlInsertHandler(field);
    }

    private List<PsiElement> getRootChildren(final PsiFile psiFile) {
        return Optional.of(psiFile)
                .filter(file -> file instanceof YAMLFile)
                .map(YAMLFile.class::cast)
                .map(yamlFile -> yamlFile.getDocuments().get(0))
                .map(yamlDocument -> yamlDocument.getYAMLElements().get(0))
                .map(psiElement -> Arrays.asList(psiElement.getChildren()))
                .orElse(Lists.newArrayList());
    }

}
