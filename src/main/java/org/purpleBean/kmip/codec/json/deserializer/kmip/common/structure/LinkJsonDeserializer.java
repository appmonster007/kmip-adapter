package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.Link;

import java.io.IOException;
import java.util.NoSuchElementException;

public class LinkJsonDeserializer extends KmipDataTypeJsonDeserializer<Link> {
    private final KmipTag kmipTag = Link.kmipTag;
    private final EncodingType encodingType = Link.encodingType;

    @Override
    public Link deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Link.class, "JSON node cannot be null for Link deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Link.class, "Invalid KMIP tag for Link");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Link.class, String.format("Failed to parse KMIP tag for Link: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Link.class,
                    String.format("Expected object with %s tag for Link, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Link.class, String.format("Missing or non-text 'type' field for Link"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(Link.class, "Link 'value' must be a non-empty array");
            return null;
        }

        Link.LinkBuilder builder = Link.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(Link.class, String.format("Failed to process field in Link: %s", e.getMessage()));
                return null;
            }
        }

        Link link = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!link.isSupported()) {
            throw new NoSuchElementException(String.format("Link is not supported for KMIP spec %s", spec));
        }

        return link;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
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