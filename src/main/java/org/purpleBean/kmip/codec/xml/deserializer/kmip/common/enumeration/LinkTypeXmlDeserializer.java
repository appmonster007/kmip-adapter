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
import org.purpleBean.kmip.common.enumeration.LinkType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for LinkType.
 */
public class LinkTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<LinkType> {
    private final KmipTag kmipTag = LinkType.kmipTag;
    private final EncodingType encodingType = LinkType.encodingType;

    @Override
    public LinkType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(LinkType.class, "Expected XML element object for LinkType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(LinkType.class, "Invalid Tag for LinkType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(LinkType.class, "Missing or invalid '@type' attribute for LinkType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(LinkType.class, "Missing or non-text '@value' attribute for LinkType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        LinkType linktype = new LinkType(LinkType.fromName(description));
        if (!linktype.isSupported()) {
            throw new NoSuchElementException(
                String.format("LinkType '%s' not supported for spec %s", description, spec));
        }

        return linktype;
    }
}
