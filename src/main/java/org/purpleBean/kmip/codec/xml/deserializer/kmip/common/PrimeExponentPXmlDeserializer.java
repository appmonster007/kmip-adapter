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
import org.purpleBean.kmip.common.PrimeExponentP;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeExponentPXmlDeserializer extends KmipDataTypeXmlDeserializer<PrimeExponentP> {
    private final KmipTag kmipTag = PrimeExponentP.kmipTag;
    private final EncodingType encodingType = PrimeExponentP.encodingType;

    @Override
    public PrimeExponentP deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PrimeExponentP.class, "Expected XML object for PrimeExponentP");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PrimeExponentP.class, "Invalid Tag for PrimeExponentP");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PrimeExponentP.class, "Missing or invalid '@type' attribute for PrimeExponentP");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrimeExponentP.class,
                    "Missing or non-text 'value' for PrimeExponentP");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        PrimeExponentP primeExponentP = PrimeExponentP.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!primeExponentP.isSupported()) {
            ctxt.reportInputMismatch(PrimeExponentP.class, "PrimeExponentP not supported for spec " + spec);
            return null;
        }

        return primeExponentP;
    }
}