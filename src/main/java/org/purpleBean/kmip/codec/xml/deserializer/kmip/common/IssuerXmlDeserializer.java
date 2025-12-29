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
import org.purpleBean.kmip.common.Issuer;

import java.io.IOException;

public class IssuerXmlDeserializer extends KmipDataTypeXmlDeserializer<Issuer> {
    private final KmipTag kmipTag = Issuer.kmipTag;
    private final EncodingType encodingType = Issuer.encodingType;

    @Override
    public Issuer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Issuer.class, "Expected XML object for Issuer");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Issuer.class, "Invalid Tag for Issuer");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Issuer.class, "Missing or invalid '@type' attribute for Issuer");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Issuer.class,
                    "Missing or non-text 'value' for Issuer");
            return null;
        }

        String value = valueNode.asText();
        Issuer issuer = Issuer.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!issuer.isSupported()) {
            ctxt.reportInputMismatch(Issuer.class, "Issuer not supported for spec " + spec);
            return null;
        }

        return issuer;
    }
}