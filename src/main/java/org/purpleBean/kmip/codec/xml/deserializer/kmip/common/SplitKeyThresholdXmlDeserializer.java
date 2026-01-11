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
import org.purpleBean.kmip.common.SplitKeyThreshold;

import java.io.IOException;

public class SplitKeyThresholdXmlDeserializer extends KmipDataTypeXmlDeserializer<SplitKeyThreshold> {
    private final KmipTag kmipTag = SplitKeyThreshold.kmipTag;
    private final EncodingType encodingType = SplitKeyThreshold.encodingType;

    @Override
    public SplitKeyThreshold deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "Invalid Tag for SplitKeyThreshold");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        SplitKeyThreshold.SplitKeyThresholdBuilder builder = SplitKeyThreshold.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(SplitKeyThreshold.class, "Missing or invalid 'type' attribute for SplitKeyThreshold");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(SplitKeyThreshold.class,
                                "Missing or non-number 'value' for SplitKeyThreshold");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        SplitKeyThreshold splitKeyThreshold = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!splitKeyThreshold.isSupported()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "SplitKeyThreshold not supported for spec " + spec);
            return null;
        }

        return splitKeyThreshold;
    }
}