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
import org.purpleBean.kmip.common.CryptographicLength;

import java.io.IOException;

public class CryptographicLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<CryptographicLength> {
    private final KmipTag kmipTag = CryptographicLength.kmipTag;
    private final EncodingType encodingType = CryptographicLength.encodingType;

    @Override
    public CryptographicLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CryptographicLength.class, "Expected XML object for CryptographicLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CryptographicLength.class, "Invalid Tag for CryptographicLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CryptographicLength.class, "Missing or invalid '@type' attribute for CryptographicLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CryptographicLength.class,
                    "Missing or non-text 'value' for CryptographicLength");
            return null;
        }

        CryptographicLength cryptographicLength;
        try {
            int length = Integer.parseInt(valueNode.asText());
            if (length < 0) {
                ctxt.reportInputMismatch(CryptographicLength.class,
                        "CryptographicLength value must be a non-negative integer");
                return null;
            }
            cryptographicLength = CryptographicLength.of(length);
        } catch (NumberFormatException e) {
            ctxt.reportInputMismatch(CryptographicLength.class,
                    "Invalid integer value for CryptographicLength: " + valueNode.asText());
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicLength.isSupported()) {
            ctxt.reportInputMismatch(CryptographicLength.class, "CryptographicLength not supported for spec " + spec);
            return null;
        }

        return cryptographicLength;
    }
}
