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
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

import java.io.IOException;

public class LinkedObjectIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<LinkedObjectIdentifier> {
    private final KmipTag kmipTag = LinkedObjectIdentifier.kmipTag;
    private final EncodingType encodingType = LinkedObjectIdentifier.encodingType;

    @Override
    public LinkedObjectIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "Expected XML object for LinkedObjectIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "Invalid Tag for LinkedObjectIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "Missing or invalid '@type' attribute for LinkedObjectIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class,
                    "Missing or non-text 'value' for LinkedObjectIdentifier");
            return null;
        }

        String value = valueNode.asText();
        LinkedObjectIdentifier linkedObjectIdentifier = LinkedObjectIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!linkedObjectIdentifier.isSupported()) {
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "LinkedObjectIdentifier not supported for spec " + spec);
            return null;
        }

        return linkedObjectIdentifier;
    }
}