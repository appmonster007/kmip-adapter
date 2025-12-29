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
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateSerialNumber> {
    private final KmipTag kmipTag = CertificateSerialNumber.kmipTag;
    private final EncodingType encodingType = CertificateSerialNumber.encodingType;

    @Override
    public CertificateSerialNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "Expected XML object for CertificateSerialNumber");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "Invalid Tag for CertificateSerialNumber");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "Missing or invalid '@type' attribute for CertificateSerialNumber");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class,
                    "Missing 'value' for CertificateSerialNumber");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        CertificateSerialNumber certificateSerialNumber = CertificateSerialNumber.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateSerialNumber.isSupported()) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "CertificateSerialNumber not supported for spec " + spec);
            return null;
        }

        return certificateSerialNumber;
    }
}
