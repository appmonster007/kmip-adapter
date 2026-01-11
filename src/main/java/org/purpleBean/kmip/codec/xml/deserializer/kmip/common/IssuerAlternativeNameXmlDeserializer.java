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
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerAlternativeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<IssuerAlternativeName> {
    private final KmipTag kmipTag = IssuerAlternativeName.kmipTag;
    private final EncodingType encodingType = IssuerAlternativeName.encodingType;

    @Override
    public IssuerAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(IssuerAlternativeName.class, "Invalid Tag for IssuerAlternativeName");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        IssuerAlternativeName.IssuerAlternativeNameBuilder builder = IssuerAlternativeName.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(IssuerAlternativeName.class, "Missing or invalid 'type' attribute for IssuerAlternativeName");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(IssuerAlternativeName.class,
                                "Missing or non-text 'value' for IssuerAlternativeName");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        IssuerAlternativeName issuerAlternativeName = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!issuerAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class, "IssuerAlternativeName not supported for spec " + spec);
            return null;
        }

        return issuerAlternativeName;
    }
}