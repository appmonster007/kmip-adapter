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
import org.purpleBean.kmip.common.PublicExponent;

import java.io.IOException;
import java.math.BigInteger;

public class PublicExponentXmlDeserializer extends KmipDataTypeXmlDeserializer<PublicExponent> {
    private final KmipTag kmipTag = PublicExponent.kmipTag;
    private final EncodingType encodingType = PublicExponent.encodingType;

    @Override
    public PublicExponent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PublicExponent.class, "Expected XML object for PublicExponent");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PublicExponent.class, "Invalid Tag for PublicExponent");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PublicExponent.class, "Missing or invalid '@type' attribute for PublicExponent");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PublicExponent.class,
                    "Missing or non-text 'value' for PublicExponent");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        PublicExponent publicExponent = PublicExponent.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!publicExponent.isSupported()) {
            ctxt.reportInputMismatch(PublicExponent.class, "PublicExponent not supported for spec " + spec);
            return null;
        }

        return publicExponent;
    }
}