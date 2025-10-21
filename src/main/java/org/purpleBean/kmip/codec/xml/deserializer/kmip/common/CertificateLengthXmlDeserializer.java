package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateLength;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class CertificateLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateLength> {
    private final KmipTag kmipTag = CertificateLength.kmipTag;
    private final EncodingType encodingType = CertificateLength.encodingType;

    @Override
    public CertificateLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateLength.class, "Expected XML object for CertificateLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateLength.class, "Invalid Tag for CertificateLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateLength.class, "Missing or invalid '@type' attribute for CertificateLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateLength.class,
                "Missing or non-text 'value' for CertificateLength");
            return null;
        }

        Integer value = valueNode.asInt();
        CertificateLength certificateLength = CertificateLength.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateLength.isSupported()) {
            ctxt.reportInputMismatch(CertificateLength.class, "CertificateLength not supported for spec " + spec);
            return null;
        }

        return certificateLength;
    }
}
