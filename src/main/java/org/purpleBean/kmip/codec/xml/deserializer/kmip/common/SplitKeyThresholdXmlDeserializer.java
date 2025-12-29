package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
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
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "Expected XML object for SplitKeyThreshold");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "Invalid Tag for SplitKeyThreshold");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "Missing or invalid '@type' attribute for SplitKeyThreshold");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class,
                    "Missing or non-number 'value' for SplitKeyThreshold");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        SplitKeyThreshold splitKeyThreshold = SplitKeyThreshold.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!splitKeyThreshold.isSupported()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "SplitKeyThreshold not supported for spec " + spec);
            return null;
        }

        return splitKeyThreshold;
    }
}