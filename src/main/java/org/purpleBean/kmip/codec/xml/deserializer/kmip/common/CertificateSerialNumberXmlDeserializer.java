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
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateSerialNumber> {
    private final KmipTag kmipTag = CertificateSerialNumber.kmipTag;
    private final EncodingType encodingType = CertificateSerialNumber.encodingType;

    @Override
    public CertificateSerialNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "Invalid Tag for CertificateSerialNumber");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CertificateSerialNumber.CertificateSerialNumberBuilder builder = CertificateSerialNumber.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CertificateSerialNumber.class, "Missing or invalid 'type' attribute for CertificateSerialNumber");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CertificateSerialNumber.class,
                                "Missing 'value' for CertificateSerialNumber");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        CertificateSerialNumber certificateSerialNumber = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!certificateSerialNumber.isSupported()) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "CertificateSerialNumber not supported for spec " + spec);
            return null;
        }

        return certificateSerialNumber;
    }
}