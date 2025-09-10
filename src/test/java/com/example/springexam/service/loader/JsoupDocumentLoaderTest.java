package com.example.springexam.service.loader;

import com.example.springexam.exception.DocumentLoadException;
import com.example.springexam.model.dto.response.html.DocumentWithFilename;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsoupDocumentLoaderTest {

    private static final String FIRST_HTML_CONTENT = "<html><body><p>First</p></body></html>";
    private static final String SECOND_HTML_CONTENT = "<html><body><p>Second</p></body></html>";
    private static final String FIRST_FILENAME = "first.html";
    private static final String SECOND_FILENAME = "second.html";
    private static final String BAD_FILENAME = "bad.html";
    private static final String IO_ERROR_MESSAGE = "fail";
    private static final String HTML_RESOURCES_FIELD = "htmlResources";
    private static final String RESOURCE_INJECTION_ERROR = "Failed to inject resources";

    @InjectMocks
    private JsoupDocumentLoader loader = new JsoupDocumentLoader();

    @Mock
    private Resource resource1;
    @Mock
    private Resource resource2;

    @Test
    void loadAllDocumentsShouldReturnParsedDocuments() throws Exception {
        mockResource(resource1, FIRST_HTML_CONTENT, FIRST_FILENAME);
        mockResource(resource2, SECOND_HTML_CONTENT, SECOND_FILENAME);

        injectTestResources(resource1, resource2);

        var docs = loader.loadAllDocuments();

        assertEquals(2, docs.size());
        assertTrue(containsText(docs, "First"));
        assertTrue(containsText(docs, "Second"));
    }

    @Test
    void loadAllDocumentsShouldReturnEmptyListWhenNoResources() {
        injectTestResources();

        var docs = loader.loadAllDocuments();

        assertNotNull(docs);
        assertTrue(docs.isEmpty());
    }

    @Test
    void loadAllDocumentsShouldSkipNullResources() {
        injectTestResources((Resource) null);

        var docs = loader.loadAllDocuments();

        assertNotNull(docs);
        assertTrue(docs.isEmpty());
    }

    @Test
    void parseResourceShouldThrowDocumentLoadException() throws Exception {
        when(resource1.getInputStream()).thenThrow(new IOException(IO_ERROR_MESSAGE));
        when(resource1.getFilename()).thenReturn(BAD_FILENAME);

        injectTestResources(resource1);

        assertThrows(DocumentLoadException.class, () -> loader.loadAllDocuments());
    }

    private void mockResource(Resource resource, String content, String filename) throws IOException {
        when(resource.getInputStream())
                .thenReturn(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
        when(resource.getFilename()).thenReturn(filename);
    }

    private void injectTestResources(Resource... resources) {
        try {
            var field = JsoupDocumentLoader.class.getDeclaredField(HTML_RESOURCES_FIELD);
            field.setAccessible(true);
            field.set(loader, resources);
        } catch (Exception e) {
            throw new RuntimeException(RESOURCE_INJECTION_ERROR, e);
        }
    }


    private boolean containsText(List<DocumentWithFilename> docs, String text) {
        return docs.stream()
                .anyMatch(doc -> doc.document().body().text().contains(text));
    }

}