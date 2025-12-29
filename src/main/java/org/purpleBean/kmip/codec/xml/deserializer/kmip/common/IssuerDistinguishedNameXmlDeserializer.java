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
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<IssuerDistinguishedName> {
    private final KmipTag kmipTag = IssuerDistinguishedName.kmipTag;
    private final EncodingType encodingType = IssuerDistinguishedName.encodingType;

    @Override
    public IssuerDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "Expected XML object for IssuerDistinguishedName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "Invalid Tag for IssuerDistinguishedName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "Missing or invalid '@type' attribute for IssuerDistinguishedName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class,
                "Missing 'value' for IssuerDistinguishedName");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        IssuerDistinguishedName issuerDistinguishedName = IssuerDistinguishedName.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!issuerDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "IssuerDistinguishedName not supported for spec " + spec);
            return null;
        }

        return issuerDistinguishedName;
    }
}
