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
import org.purpleBean.kmip.common.D;

import java.io.IOException;
import java.math.BigInteger;

public class DXmlDeserializer extends KmipDataTypeXmlDeserializer<D> {
    private final KmipTag kmipTag = D.kmipTag;
    private final EncodingType encodingType = D.encodingType;

    @Override
    public D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(D.class, "Expected XML object for D");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(D.class, "Invalid Tag for D");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(D.class, "Missing or invalid '@type' attribute for D");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(D.class,
                    "Missing or non-text 'value' for D");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        D d = D.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!d.isSupported()) {
            ctxt.reportInputMismatch(D.class, "D not supported for spec " + spec);
            return null;
        }

        return d;
    }
}