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
import org.purpleBean.kmip.common.CRTCoefficient;

import java.io.IOException;
import java.math.BigInteger;

public class CRTCoefficientXmlDeserializer extends KmipDataTypeXmlDeserializer<CRTCoefficient> {
    private final KmipTag kmipTag = CRTCoefficient.kmipTag;
    private final EncodingType encodingType = CRTCoefficient.encodingType;

    @Override
    public CRTCoefficient deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CRTCoefficient.class, "Expected XML object for CRTCoefficient");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CRTCoefficient.class, "Invalid Tag for CRTCoefficient");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CRTCoefficient.class, "Missing or invalid '@type' attribute for CRTCoefficient");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CRTCoefficient.class,
                    "Missing or non-text 'value' for CRTCoefficient");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        CRTCoefficient cRTCoefficient = CRTCoefficient.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!cRTCoefficient.isSupported()) {
            ctxt.reportInputMismatch(CRTCoefficient.class, "CRTCoefficient not supported for spec " + spec);
            return null;
        }

        return cRTCoefficient;
    }
}