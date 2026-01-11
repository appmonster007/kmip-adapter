package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
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
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "Invalid Tag for NetworkIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        NetworkIdentifier.NetworkIdentifierBuilder builder = NetworkIdentifier.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(NetworkIdentifier.class, "Missing or invalid 'type' attribute for NetworkIdentifier");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(NetworkIdentifier.class,
                                "Missing or non-text 'value' for NetworkIdentifier");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        NetworkIdentifier networkIdentifier = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!networkIdentifier.isSupported()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "NetworkIdentifier not supported for spec " + spec);
            return null;
        }

        return networkIdentifier;
    }
}