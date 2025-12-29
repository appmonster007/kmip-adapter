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
import org.purpleBean.kmip.common.Y;

import java.io.IOException;
import java.math.BigInteger;

public class YXmlDeserializer extends KmipDataTypeXmlDeserializer<Y> {
    private final KmipTag kmipTag = Y.kmipTag;
    private final EncodingType encodingType = Y.encodingType;

    @Override
    public Y deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Y.class, "Expected XML object for Y");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Y.class, "Invalid Tag for Y");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Y.class, "Missing or invalid '@type' attribute for Y");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Y.class,
                    "Missing or non-text 'value' for Y");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        Y y = Y.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!y.isSupported()) {
            ctxt.reportInputMismatch(Y.class, "Y not supported for spec " + spec);
            return null;
        }

        return y;
    }
}