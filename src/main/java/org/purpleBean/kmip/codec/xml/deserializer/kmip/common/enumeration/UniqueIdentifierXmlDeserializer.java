package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

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
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for UniqueIdentifier.
 */
public class UniqueIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<UniqueIdentifier> {
    private final KmipTag kmipTag = UniqueIdentifier.kmipTag;
    private final EncodingType encodingType = UniqueIdentifier.encodingType;

    @Override
    public UniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "Expected XML element object for UniqueIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "Invalid Tag for UniqueIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "Missing or invalid '@type' attribute for UniqueIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "Missing or non-text '@value' attribute for UniqueIdentifier");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        UniqueIdentifier uniqueidentifier = new UniqueIdentifier(UniqueIdentifier.fromName(description));
        if (!uniqueidentifier.isSupported()) {
            throw new NoSuchElementException(
                String.format("UniqueIdentifier '%s' not supported for spec %s", description, spec));
        }

        return uniqueidentifier;
    }
}
