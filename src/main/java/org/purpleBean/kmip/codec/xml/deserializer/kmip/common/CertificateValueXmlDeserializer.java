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
import org.purpleBean.kmip.common.CertificateValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateValueXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateValue> {
    private final KmipTag kmipTag = CertificateValue.kmipTag;
    private final EncodingType encodingType = CertificateValue.encodingType;

    @Override
    public CertificateValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateValue.class, "Expected XML object for CertificateValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateValue.class, "Invalid Tag for CertificateValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateValue.class, "Missing or invalid '@type' attribute for CertificateValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateValue.class,
                    "Missing or non-text 'value' for CertificateValue");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        CertificateValue certificateValue = CertificateValue.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateValue.isSupported()) {
            ctxt.reportInputMismatch(CertificateValue.class, "CertificateValue not supported for spec " + spec);
            return null;
        }

        return certificateValue;
    }
}