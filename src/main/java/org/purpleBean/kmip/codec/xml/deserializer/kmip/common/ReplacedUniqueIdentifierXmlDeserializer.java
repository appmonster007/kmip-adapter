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
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

import java.io.IOException;

public class ReplacedUniqueIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<ReplacedUniqueIdentifier> {
    private final KmipTag kmipTag = ReplacedUniqueIdentifier.kmipTag;
    private final EncodingType encodingType = ReplacedUniqueIdentifier.encodingType;

    @Override
    public ReplacedUniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, "Expected XML object for ReplacedUniqueIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, "Invalid Tag for ReplacedUniqueIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, "Missing or invalid '@type' attribute for ReplacedUniqueIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class,
                    "Missing or non-text 'value' for ReplacedUniqueIdentifier");
            return null;
        }

        String value = valueNode.asText();
        ReplacedUniqueIdentifier replacedUniqueIdentifier = ReplacedUniqueIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!replacedUniqueIdentifier.isSupported()) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, "ReplacedUniqueIdentifier not supported for spec " + spec);
            return null;
        }

        return replacedUniqueIdentifier;
    }
}