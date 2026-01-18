package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SymmetricKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SymmetricKey, SymmetricKey.SymmetricKeyBuilder> {

    public SymmetricKeyTtlvDeserializer() {
        super(SymmetricKey.kmipTag);
    }

    @Override
    protected SymmetricKey.SymmetricKeyBuilder createBuilder() {
        return SymmetricKey.builder();
    }

    @Override
    protected void setValue(SymmetricKey.SymmetricKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SymmetricKey build(SymmetricKey.SymmetricKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SymmetricKey.encodingType;
    }
}