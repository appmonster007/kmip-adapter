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
import org.purpleBean.kmip.common.P;

import java.io.IOException;
import java.math.BigInteger;

public class PXmlDeserializer extends KmipDataTypeXmlDeserializer<P> {
    private final KmipTag kmipTag = P.kmipTag;
    private final EncodingType encodingType = P.encodingType;

    @Override
    public P deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(P.class, "Expected XML object for P");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(P.class, "Invalid Tag for P");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(P.class, "Missing or invalid '@type' attribute for P");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(P.class,
                    "Missing or non-text 'value' for P");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        P result = P.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!result.isSupported()) {
            ctxt.reportInputMismatch(P.class, "P not supported for spec " + spec);
            return null;
        }

        return result;
    }
}