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
import org.purpleBean.kmip.common.Modulus;

import java.io.IOException;
import java.math.BigInteger;

public class ModulusXmlDeserializer extends KmipDataTypeXmlDeserializer<Modulus> {
    private final KmipTag kmipTag = Modulus.kmipTag;
    private final EncodingType encodingType = Modulus.encodingType;

    @Override
    public Modulus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Modulus.class, "Expected XML object for Modulus");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Modulus.class, "Invalid Tag for Modulus");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Modulus.class, "Missing or invalid '@type' attribute for Modulus");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Modulus.class,
                    "Missing or non-text 'value' for Modulus");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        Modulus modulus = Modulus.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!modulus.isSupported()) {
            ctxt.reportInputMismatch(Modulus.class, "Modulus not supported for spec " + spec);
            return null;
        }

        return modulus;
    }
}