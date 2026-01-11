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
import org.purpleBean.kmip.common.IterationCount;

import java.io.IOException;

public class IterationCountXmlDeserializer extends KmipDataTypeXmlDeserializer<IterationCount> {
    private final KmipTag kmipTag = IterationCount.kmipTag;
    private final EncodingType encodingType = IterationCount.encodingType;

    @Override
    public IterationCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(IterationCount.class, "Invalid Tag for IterationCount");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        IterationCount.IterationCountBuilder builder = IterationCount.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(IterationCount.class, "Missing or invalid 'type' attribute for IterationCount");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(IterationCount.class,
                                "Missing or non-number 'value' for IterationCount");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        IterationCount iterationCount = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!iterationCount.isSupported()) {
            ctxt.reportInputMismatch(IterationCount.class, "IterationCount not supported for spec " + spec);
            return null;
        }

        return iterationCount;
    }
}