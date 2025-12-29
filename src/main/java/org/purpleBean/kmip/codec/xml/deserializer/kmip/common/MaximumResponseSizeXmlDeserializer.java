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
import org.purpleBean.kmip.common.MaximumResponseSize;

import java.io.IOException;

public class MaximumResponseSizeXmlDeserializer extends KmipDataTypeXmlDeserializer<MaximumResponseSize> {
    private final KmipTag kmipTag = MaximumResponseSize.kmipTag;
    private final EncodingType encodingType = MaximumResponseSize.encodingType;

    @Override
    public MaximumResponseSize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "Expected XML object for MaximumResponseSize");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "Invalid Tag for MaximumResponseSize");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "Missing or invalid '@type' attribute for MaximumResponseSize");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class,
                    "Missing or non-number 'value' for MaximumResponseSize");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        MaximumResponseSize maximumResponseSize = MaximumResponseSize.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!maximumResponseSize.isSupported()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "MaximumResponseSize not supported for spec " + spec);
            return null;
        }

        return maximumResponseSize;
    }
}