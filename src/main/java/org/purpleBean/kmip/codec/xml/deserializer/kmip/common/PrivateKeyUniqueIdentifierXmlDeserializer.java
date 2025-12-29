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
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

import java.io.IOException;

public class PrivateKeyUniqueIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<PrivateKeyUniqueIdentifier> {
    private final KmipTag kmipTag = PrivateKeyUniqueIdentifier.kmipTag;
    private final EncodingType encodingType = PrivateKeyUniqueIdentifier.encodingType;

    @Override
    public PrivateKeyUniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PrivateKeyUniqueIdentifier.class, "Expected XML object for PrivateKeyUniqueIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PrivateKeyUniqueIdentifier.class, "Invalid Tag for PrivateKeyUniqueIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PrivateKeyUniqueIdentifier.class, "Missing or invalid '@type' attribute for PrivateKeyUniqueIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrivateKeyUniqueIdentifier.class,
                    "Missing or non-text 'value' for PrivateKeyUniqueIdentifier");
            return null;
        }

        String value = valueNode.asText();
        PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier = PrivateKeyUniqueIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!privateKeyUniqueIdentifier.isSupported()) {
            ctxt.reportInputMismatch(PrivateKeyUniqueIdentifier.class, "PrivateKeyUniqueIdentifier not supported for spec " + spec);
            return null;
        }

        return privateKeyUniqueIdentifier;
    }
}