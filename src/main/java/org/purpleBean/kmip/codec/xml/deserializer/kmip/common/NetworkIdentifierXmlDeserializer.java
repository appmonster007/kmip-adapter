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
import org.purpleBean.kmip.common.NetworkIdentifier;

import java.io.IOException;

public class NetworkIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<NetworkIdentifier> {
    private final KmipTag kmipTag = NetworkIdentifier.kmipTag;
    private final EncodingType encodingType = NetworkIdentifier.encodingType;

    @Override
    public NetworkIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "Expected XML object for NetworkIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "Invalid Tag for NetworkIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "Missing or invalid '@type' attribute for NetworkIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class,
                    "Missing or non-text 'value' for NetworkIdentifier");
            return null;
        }

        String value = valueNode.asText();
        NetworkIdentifier networkIdentifier = NetworkIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!networkIdentifier.isSupported()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "NetworkIdentifier not supported for spec " + spec);
            return null;
        }

        return networkIdentifier;
    }
}