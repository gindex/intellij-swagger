package org.zalando.intellij.swagger.completion.traversal;

import com.intellij.json.psi.JsonFile;
import com.intellij.json.psi.JsonProperty;
import com.intellij.json.psi.JsonValue;
import com.intellij.psi.PsiElement;
import org.junit.Before;
import org.junit.Test;
import org.zalando.intellij.swagger.completion.traversal.JsonTraversal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonTraversalTest {

    private JsonTraversal jsonTraversal;

    @Before
    public void setUp() throws Exception {
        jsonTraversal = new JsonTraversal();
    }

    @Test
    public void thatRootIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonFile jsonFile = mock(JsonFile.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonFile);

        assertTrue(jsonTraversal.isRoot(psiElement1));
    }

    @Test
    public void thatKeyIsResolved() {
        final PsiElement psiElement = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);
        final JsonValue jsonValue = mock(JsonValue.class);

        when(psiElement.getParent()).thenReturn(jsonValue);
        when(jsonValue.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getNameElement()).thenReturn(jsonValue);

        assertTrue(jsonTraversal.isKey(psiElement));
    }

    @Test
    public void thatInfoIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("info");

        assertTrue(jsonTraversal.isInfo(psiElement1));
    }

    @Test
    public void thatLicenseIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("license");

        assertTrue(jsonTraversal.isLicense(psiElement1));
    }

    @Test
    public void thatPathIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final PsiElement psiElement6 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(psiElement6);
        when(psiElement6.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("paths");

        assertTrue(jsonTraversal.isPath(psiElement1));
    }

    @Test
    public void thatExternalDocsIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("externalDocs");

        assertTrue(jsonTraversal.isExternalDocs(psiElement1));
    }

    @Test
    public void thatParametersIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("parameters");

        assertTrue(jsonTraversal.isParameters(psiElement1));
    }

    @Test
    public void thatItemsIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("items");

        assertTrue(jsonTraversal.isItems(psiElement1));
    }

    @Test
    public void thatResponsesIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("responses");

        assertTrue(jsonTraversal.isResponses(psiElement1));
    }

    @Test
    public void thatResponseIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final PsiElement psiElement6 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(psiElement6);
        when(psiElement6.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("responses");

        assertTrue(jsonTraversal.isResponse(psiElement1));
    }

    @Test
    public void thatHeaderIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final PsiElement psiElement6 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(psiElement6);
        when(psiElement6.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("headers");

        assertTrue(jsonTraversal.isHeader(psiElement1));
    }

    @Test
    public void thatTagIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("tags");

        assertTrue(jsonTraversal.isTag(psiElement1));
    }

    @Test
    public void thatSecurityDefinitionsIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final PsiElement psiElement6 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(psiElement6);
        when(psiElement6.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("securityDefinitions");

        assertTrue(jsonTraversal.isSecurityDefinition(psiElement1));
    }

    @Test
    public void thatSchemaIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("schema");

        assertTrue(jsonTraversal.isSchema(psiElement1));
    }

    @Test
    public void thatDefinitionsIsResolved() {
        final PsiElement psiElement1 = mock(PsiElement.class);
        final PsiElement psiElement2 = mock(PsiElement.class);
        final PsiElement psiElement3 = mock(PsiElement.class);
        final PsiElement psiElement4 = mock(PsiElement.class);
        final PsiElement psiElement5 = mock(PsiElement.class);
        final PsiElement psiElement6 = mock(PsiElement.class);
        final JsonProperty jsonProperty = mock(JsonProperty.class);

        when(psiElement1.getParent()).thenReturn(psiElement2);
        when(psiElement2.getParent()).thenReturn(psiElement3);
        when(psiElement3.getParent()).thenReturn(psiElement4);
        when(psiElement4.getParent()).thenReturn(psiElement5);
        when(psiElement5.getParent()).thenReturn(psiElement6);
        when(psiElement6.getParent()).thenReturn(jsonProperty);
        when(jsonProperty.getName()).thenReturn("definitions");

        assertTrue(jsonTraversal.isDefinitions(psiElement1));
    }
}
