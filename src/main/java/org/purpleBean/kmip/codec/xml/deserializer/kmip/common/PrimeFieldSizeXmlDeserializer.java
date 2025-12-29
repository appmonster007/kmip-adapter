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
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeFieldSizeXmlDeserializer extends KmipDataTypeXmlDeserializer<PrimeFieldSize> {
    private final KmipTag kmipTag = PrimeFieldSize.kmipTag;
    private final EncodingType encodingType = PrimeFieldSize.encodingType;

    @Override
    public PrimeFieldSize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "Expected XML object for PrimeFieldSize");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "Invalid Tag for PrimeFieldSize");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "Missing or invalid '@type' attribute for PrimeFieldSize");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class,
                    "Missing or non-text 'value' for PrimeFieldSize");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        PrimeFieldSize primeFieldSize = PrimeFieldSize.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!primeFieldSize.isSupported()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "PrimeFieldSize not supported for spec " + spec);
            return null;
        }

        return primeFieldSize;
    }
}