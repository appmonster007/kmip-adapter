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
import org.purpleBean.kmip.common.Q;

import java.io.IOException;
import java.math.BigInteger;

public class QXmlDeserializer extends KmipDataTypeXmlDeserializer<Q> {
    private final KmipTag kmipTag = Q.kmipTag;
    private final EncodingType encodingType = Q.encodingType;

    @Override
    public Q deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Q.class, "Expected XML object for Q");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Q.class, "Invalid Tag for Q");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Q.class, "Missing or invalid '@type' attribute for Q");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Q.class,
                    "Missing or non-text 'value' for Q");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        Q q = Q.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!q.isSupported()) {
            ctxt.reportInputMismatch(Q.class, "Q not supported for spec " + spec);
            return null;
        }

        return q;
    }
}