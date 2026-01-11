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
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

import java.io.IOException;

public class PublicKeyUniqueIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<PublicKeyUniqueIdentifier> {
    private final KmipTag kmipTag = PublicKeyUniqueIdentifier.kmipTag;
    private final EncodingType encodingType = PublicKeyUniqueIdentifier.encodingType;

    @Override
    public PublicKeyUniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "Invalid Tag for PublicKeyUniqueIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder = PublicKeyUniqueIdentifier.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "Missing or invalid 'type' attribute for PublicKeyUniqueIdentifier");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class,
                                "Missing or non-text 'value' for PublicKeyUniqueIdentifier");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        PublicKeyUniqueIdentifier publicKeyUniqueIdentifier = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!publicKeyUniqueIdentifier.isSupported()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "PublicKeyUniqueIdentifier not supported for spec " + spec);
            return null;
        }

        return publicKeyUniqueIdentifier;
    }
}