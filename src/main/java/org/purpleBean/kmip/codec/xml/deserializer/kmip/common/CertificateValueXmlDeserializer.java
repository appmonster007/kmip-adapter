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
import org.purpleBean.kmip.common.CertificateValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateValueXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateValue> {
    private final KmipTag kmipTag = CertificateValue.kmipTag;
    private final EncodingType encodingType = CertificateValue.encodingType;

    @Override
    public CertificateValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateValue.class, "Invalid Tag for CertificateValue");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CertificateValue.CertificateValueBuilder builder = CertificateValue.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CertificateValue.class, "Missing or invalid 'type' attribute for CertificateValue");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CertificateValue.class,
                                "Missing or non-text 'value' for CertificateValue");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        CertificateValue certificateValue = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!certificateValue.isSupported()) {
            ctxt.reportInputMismatch(CertificateValue.class, "CertificateValue not supported for spec " + spec);
            return null;
        }

        return certificateValue;
    }
}