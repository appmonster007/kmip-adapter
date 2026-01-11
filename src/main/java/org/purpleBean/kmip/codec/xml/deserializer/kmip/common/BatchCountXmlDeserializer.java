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
import org.purpleBean.kmip.common.BatchCount;

import java.io.IOException;

public class BatchCountXmlDeserializer extends KmipDataTypeXmlDeserializer<BatchCount> {
    private final KmipTag kmipTag = BatchCount.kmipTag;
    private final EncodingType encodingType = BatchCount.encodingType;

    @Override
    public BatchCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(BatchCount.class, "Invalid Tag for BatchCount");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        BatchCount.BatchCountBuilder builder = BatchCount.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(BatchCount.class, "Missing or invalid 'type' attribute for BatchCount");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(BatchCount.class,
                                "Missing or non-text 'value' for BatchCount");
                        return null;
                    }
                    try {
                        int value = Integer.parseInt(p.getText());
                        if (value < 0) {
                            ctxt.reportInputMismatch(BatchCount.class, "BatchCount value must be a non-negative integer");
                            return null;
                        }
                        builder.value(value);
                    } catch (NumberFormatException e) {
                        ctxt.reportInputMismatch(BatchCount.class, "Invalid integer value for BatchCount: " + p.getText());
                        return null;
                    }
                }
            }
        }

        BatchCount batchCount = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!batchCount.isSupported()) {
            ctxt.reportInputMismatch(BatchCount.class, "BatchCount not supported for spec " + spec);
            return null;
        }

        return batchCount;
    }
}