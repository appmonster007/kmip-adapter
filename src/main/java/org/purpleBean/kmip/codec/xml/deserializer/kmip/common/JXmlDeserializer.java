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
import org.purpleBean.kmip.common.J;

import java.io.IOException;
import java.math.BigInteger;

public class JXmlDeserializer extends KmipDataTypeXmlDeserializer<J> {
    private final KmipTag kmipTag = J.kmipTag;
    private final EncodingType encodingType = J.encodingType;

    @Override
    public J deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(J.class, "Expected XML object for J");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(J.class, "Invalid Tag for J");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(J.class, "Missing or invalid '@type' attribute for J");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(J.class,
                    "Missing or non-text 'value' for J");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        J j = J.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!j.isSupported()) {
            ctxt.reportInputMismatch(J.class, "J not supported for spec " + spec);
            return null;
        }

        return j;
    }
}