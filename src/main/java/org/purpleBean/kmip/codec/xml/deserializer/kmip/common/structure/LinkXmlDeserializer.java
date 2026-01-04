package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.Link;

import java.io.IOException;
import java.util.Map;

public class LinkXmlDeserializer extends KmipDataTypeXmlDeserializer<Link> {
    private final KmipTag kmipTag = Link.kmipTag;
    private final EncodingType encodingType = Link.encodingType;

    @Override
    public Link deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Link.class, "Expected XML object for Link");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Link.class, "Invalid Tag for Link");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        Link.LinkBuilder builder = Link.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        Link link = builder.build();

        if (!link.isSupported()) {
            ctxt.reportInputMismatch(Link.class, "Link not supported for spec " + spec);
            return null;
        }

        return link;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the XML node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            Link.LinkBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.LINK_TYPE -> builder.linkType(p.getCodec().treeToValue(node, LinkType.class));
            case KmipTag.Standard.LINKED_OBJECT_IDENTIFIER -> builder.linkedObjectIdentifier(p.getCodec().treeToValue(node, LinkedObjectIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}