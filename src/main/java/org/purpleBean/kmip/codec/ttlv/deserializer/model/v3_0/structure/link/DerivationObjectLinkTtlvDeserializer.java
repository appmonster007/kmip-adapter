package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.link;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.DerivationObjectLink;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DerivationObjectLinkTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DerivationObjectLink, DerivationObjectLink.DerivationObjectLinkBuilder> {

    public DerivationObjectLinkTtlvDeserializer() {
        super(DerivationObjectLink.kmipTag, DerivationObjectLink.encodingType);
    }

    @Override
    protected DerivationObjectLink.DerivationObjectLinkBuilder createBuilder() {
        return DerivationObjectLink.builder();
    }

    @Override
    protected void setValue(DerivationObjectLink.DerivationObjectLinkBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DerivationObjectLink build(DerivationObjectLink.DerivationObjectLinkBuilder builder) {
        return builder.build();
    }
}
