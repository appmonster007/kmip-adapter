package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PrivateKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PrivateKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<PrivateKey, PrivateKey.PrivateKeyBuilder> {

    public PrivateKeyTtlvDeserializer() {
        super(PrivateKey.kmipTag);
    }

    @Override
    protected PrivateKey.PrivateKeyBuilder createBuilder() {
        return PrivateKey.builder();
    }

    @Override
    protected void setValue(PrivateKey.PrivateKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PrivateKey build(PrivateKey.PrivateKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return PrivateKey.encodingType;
    }
}