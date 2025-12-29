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
import org.purpleBean.kmip.common.CertificateRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateRequestXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateRequest> {
    private final KmipTag kmipTag = CertificateRequest.kmipTag;
    private final EncodingType encodingType = CertificateRequest.encodingType;

    @Override
    public CertificateRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateRequest.class, "Expected XML object for CertificateRequest");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateRequest.class, "Invalid Tag for CertificateRequest");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateRequest.class, "Missing or invalid '@type' attribute for CertificateRequest");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateRequest.class,
                    "Missing or non-text 'value' for CertificateRequest");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        CertificateRequest certificateRequest = CertificateRequest.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateRequest.isSupported()) {
            ctxt.reportInputMismatch(CertificateRequest.class, "CertificateRequest not supported for spec " + spec);
            return null;
        }

        return certificateRequest;
    }
}