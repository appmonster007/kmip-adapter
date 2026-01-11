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
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<IssuerDistinguishedName> {
    private final KmipTag kmipTag = IssuerDistinguishedName.kmipTag;
    private final EncodingType encodingType = IssuerDistinguishedName.encodingType;

    @Override
    public IssuerDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "Invalid Tag for IssuerDistinguishedName");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder = IssuerDistinguishedName.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(IssuerDistinguishedName.class, "Missing or invalid 'type' attribute for IssuerDistinguishedName");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(IssuerDistinguishedName.class,
                                "Missing 'value' for IssuerDistinguishedName");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        IssuerDistinguishedName issuerDistinguishedName = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!issuerDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "IssuerDistinguishedName not supported for spec " + spec);
            return null;
        }

        return issuerDistinguishedName;
    }
}