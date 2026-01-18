package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.structure.Link;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LinkTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<Link, Link.LinkBuilder> {

    public LinkTtlvDeserializer() {
        super(Link.kmipTag);
    }

    @Override
    protected Link.LinkBuilder createBuilder() {
        return Link.builder();
    }

    @Override
    protected void setValue(Link.LinkBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.LINK_TYPE -> builder.linkType(mapper.readValue(p, LinkType.class));
            case KmipTag.Standard.LINKED_OBJECT_IDENTIFIER ->
                    builder.linkedObjectIdentifier(mapper.readValue(p, LinkedObjectIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Link build(Link.LinkBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return Link.encodingType;
    }
}