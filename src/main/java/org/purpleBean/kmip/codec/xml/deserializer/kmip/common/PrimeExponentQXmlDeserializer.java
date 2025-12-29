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
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeExponentQXmlDeserializer extends KmipDataTypeXmlDeserializer<PrimeExponentQ> {
    private final KmipTag kmipTag = PrimeExponentQ.kmipTag;
    private final EncodingType encodingType = PrimeExponentQ.encodingType;

    @Override
    public PrimeExponentQ deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, "Expected XML object for PrimeExponentQ");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, "Invalid Tag for PrimeExponentQ");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, "Missing or invalid '@type' attribute for PrimeExponentQ");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrimeExponentQ.class,
                    "Missing or non-text 'value' for PrimeExponentQ");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        PrimeExponentQ primeExponentQ = PrimeExponentQ.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!primeExponentQ.isSupported()) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, "PrimeExponentQ not supported for spec " + spec);
            return null;
        }

        return primeExponentQ;
    }
}