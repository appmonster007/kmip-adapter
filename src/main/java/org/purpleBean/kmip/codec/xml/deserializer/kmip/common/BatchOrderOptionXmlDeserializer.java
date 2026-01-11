package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.BatchOrderOption;

import java.io.IOException;

public class BatchOrderOptionXmlDeserializer extends KmipDataTypeXmlDeserializer<BatchOrderOption> {
    private final KmipTag kmipTag = BatchOrderOption.kmipTag;
    private final EncodingType encodingType = BatchOrderOption.encodingType;

    @Override
    public BatchOrderOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "Invalid Tag for BatchOrderOption");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        BatchOrderOption.BatchOrderOptionBuilder builder = BatchOrderOption.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(BatchOrderOption.class, "Missing or invalid 'type' attribute for BatchOrderOption");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(BatchOrderOption.class,
                                "Missing or non-boolean 'value' for BatchOrderOption");
                        return null;
                    }
                    builder.value(Boolean.parseBoolean(p.getText()));
                }
            }
        }

        BatchOrderOption batchOrderOption = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!batchOrderOption.isSupported()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "BatchOrderOption not supported for spec " + spec);
            return null;
        }

        return batchOrderOption;
    }
}