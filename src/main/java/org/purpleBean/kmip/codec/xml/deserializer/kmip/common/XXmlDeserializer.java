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
import org.purpleBean.kmip.common.X;

import java.io.IOException;
import java.math.BigInteger;

public class XXmlDeserializer extends KmipDataTypeXmlDeserializer<X> {
    private final KmipTag kmipTag = X.kmipTag;
    private final EncodingType encodingType = X.encodingType;

    @Override
    public X deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(X.class, "Expected XML object for X");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(X.class, "Invalid Tag for X");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(X.class, "Missing or invalid '@type' attribute for X");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(X.class,
                    "Missing or non-text 'value' for X");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        X x = X.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!x.isSupported()) {
            ctxt.reportInputMismatch(X.class, "X not supported for spec " + spec);
            return null;
        }

        return x;
    }
}