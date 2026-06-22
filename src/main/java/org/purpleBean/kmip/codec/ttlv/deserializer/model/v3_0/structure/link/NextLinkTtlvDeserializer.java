package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.link;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.NextLink;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NextLinkTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NextLink, NextLink.NextLinkBuilder> {

    public NextLinkTtlvDeserializer() {
        super(NextLink.kmipTag, NextLink.encodingType);
    }

    @Override
    protected NextLink.NextLinkBuilder createBuilder() {
        return NextLink.builder();
    }

    @Override
    protected void setValue(NextLink.NextLinkBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected NextLink build(NextLink.NextLinkBuilder builder) {
        return builder.build();
    }
}
