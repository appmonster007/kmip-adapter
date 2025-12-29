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
import org.purpleBean.kmip.common.PrivateExponent;

import java.io.IOException;
import java.math.BigInteger;

public class PrivateExponentXmlDeserializer extends KmipDataTypeXmlDeserializer<PrivateExponent> {
    private final KmipTag kmipTag = PrivateExponent.kmipTag;
    private final EncodingType encodingType = PrivateExponent.encodingType;

    @Override
    public PrivateExponent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PrivateExponent.class, "Expected XML object for PrivateExponent");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PrivateExponent.class, "Invalid Tag for PrivateExponent");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PrivateExponent.class, "Missing or invalid '@type' attribute for PrivateExponent");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrivateExponent.class,
                    "Missing or non-text 'value' for PrivateExponent");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        PrivateExponent privateExponent = PrivateExponent.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!privateExponent.isSupported()) {
            ctxt.reportInputMismatch(PrivateExponent.class, "PrivateExponent not supported for spec " + spec);
            return null;
        }

        return privateExponent;
    }
}