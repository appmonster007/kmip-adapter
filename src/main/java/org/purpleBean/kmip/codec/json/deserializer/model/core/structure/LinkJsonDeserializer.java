package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.structure.Link;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

import java.io.IOException;

public class LinkJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Link, Link.LinkBuilder> {

    public LinkJsonDeserializer() {
        super(Link.kmipTag, Link.encodingType);
    }

    @Override
    protected Link.LinkBuilder createBuilder() {
        return Link.builder();
    }

    @Override
    protected void setValue(Link.LinkBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.LINK_TYPE -> builder.linkType(ctxt.readValue(p, LinkType.class));
            case KmipTag.Standard.LINKED_OBJECT_IDENTIFIER ->
                    builder.linkedObjectIdentifier(ctxt.readValue(p, LinkedObjectIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Link build(Link.LinkBuilder builder) {
        return builder.build();
    }
}