package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.link;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.DerivedObjectLink;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DerivedObjectLinkTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DerivedObjectLink, DerivedObjectLink.DerivedObjectLinkBuilder> {

    public DerivedObjectLinkTtlvDeserializer() {
        super(DerivedObjectLink.kmipTag, DerivedObjectLink.encodingType);
    }

    @Override
    protected DerivedObjectLink.DerivedObjectLinkBuilder createBuilder() {
        return DerivedObjectLink.builder();
    }

    @Override
    protected void setValue(DerivedObjectLink.DerivedObjectLinkBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DerivedObjectLink build(DerivedObjectLink.DerivedObjectLinkBuilder builder) {
        return builder.build();
    }
}
