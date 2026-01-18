package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<PublicKey, PublicKey.PublicKeyBuilder> {

    public PublicKeyTtlvDeserializer() {
        super(PublicKey.kmipTag);
    }

    @Override
    protected PublicKey.PublicKeyBuilder createBuilder() {
        return PublicKey.builder();
    }

    @Override
    protected void setValue(PublicKey.PublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PublicKey build(PublicKey.PublicKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return PublicKey.encodingType;
    }
}