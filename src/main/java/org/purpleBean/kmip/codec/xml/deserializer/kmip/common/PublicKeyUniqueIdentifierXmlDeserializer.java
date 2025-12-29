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
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

import java.io.IOException;

public class PublicKeyUniqueIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<PublicKeyUniqueIdentifier> {
    private final KmipTag kmipTag = PublicKeyUniqueIdentifier.kmipTag;
    private final EncodingType encodingType = PublicKeyUniqueIdentifier.encodingType;

    @Override
    public PublicKeyUniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "Expected XML object for PublicKeyUniqueIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "Invalid Tag for PublicKeyUniqueIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "Missing or invalid '@type' attribute for PublicKeyUniqueIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class,
                    "Missing or non-text 'value' for PublicKeyUniqueIdentifier");
            return null;
        }

        String value = valueNode.asText();
        PublicKeyUniqueIdentifier publicKeyUniqueIdentifier = PublicKeyUniqueIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!publicKeyUniqueIdentifier.isSupported()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "PublicKeyUniqueIdentifier not supported for spec " + spec);
            return null;
        }

        return publicKeyUniqueIdentifier;
    }
}