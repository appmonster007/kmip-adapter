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
import org.purpleBean.kmip.common.CertificateRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateRequestXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateRequest> {
    private final KmipTag kmipTag = CertificateRequest.kmipTag;
    private final EncodingType encodingType = CertificateRequest.encodingType;

    @Override
    public CertificateRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateRequest.class, "Invalid Tag for CertificateRequest");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CertificateRequest.CertificateRequestBuilder builder = CertificateRequest.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CertificateRequest.class, "Missing or invalid 'type' attribute for CertificateRequest");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CertificateRequest.class,
                                "Missing or non-text 'value' for CertificateRequest");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        CertificateRequest certificateRequest = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!certificateRequest.isSupported()) {
            ctxt.reportInputMismatch(CertificateRequest.class, "CertificateRequest not supported for spec " + spec);
            return null;
        }

        return certificateRequest;
    }
}