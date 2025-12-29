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
import org.purpleBean.kmip.common.CriticalityIndicator;

import java.io.IOException;

public class CriticalityIndicatorXmlDeserializer extends KmipDataTypeXmlDeserializer<CriticalityIndicator> {
    private final KmipTag kmipTag = CriticalityIndicator.kmipTag;
    private final EncodingType encodingType = CriticalityIndicator.encodingType;

    @Override
    public CriticalityIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "Expected XML object for CriticalityIndicator");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "Invalid Tag for CriticalityIndicator");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "Missing or invalid '@type' attribute for CriticalityIndicator");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class,
                    "Missing or non-boolean 'value' for CriticalityIndicator");
            return null;
        }

        boolean value = Boolean.parseBoolean(valueNode.asText());
        CriticalityIndicator criticalityIndicator = CriticalityIndicator.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!criticalityIndicator.isSupported()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "CriticalityIndicator not supported for spec " + spec);
            return null;
        }

        return criticalityIndicator;
    }
}