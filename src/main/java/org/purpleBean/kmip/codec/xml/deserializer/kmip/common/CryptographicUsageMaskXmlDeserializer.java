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
import org.purpleBean.kmip.common.CryptographicUsageMask;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class CryptographicUsageMaskXmlDeserializer extends KmipDataTypeXmlDeserializer<CryptographicUsageMask> {
    private final KmipTag kmipTag = CryptographicUsageMask.kmipTag;
    private final EncodingType encodingType = CryptographicUsageMask.encodingType;

    @Override
    public CryptographicUsageMask deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "Expected XML object for CryptographicUsageMask");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "Invalid Tag for CryptographicUsageMask");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "Missing or invalid '@type' attribute for CryptographicUsageMask");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class,
                "Missing or non-text 'value' for CryptographicUsageMask");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        CryptographicUsageMask cryptographicUsageMask = CryptographicUsageMask.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!cryptographicUsageMask.isSupported()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "CryptographicUsageMask not supported for spec " + spec);
            return null;
        }

        return cryptographicUsageMask;
    }
}
