package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PublicKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PublicKey, PublicKey.PublicKeyBuilder> {

    public PublicKeyTtlvDeserializer() {
        super(PublicKey.kmipTag, PublicKey.encodingType);
    }

    @Override
    protected PublicKey.PublicKeyBuilder createBuilder() {
        return PublicKey.builder();
    }

    @Override
    protected void setValue(PublicKey.PublicKeyBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PublicKey build(PublicKey.PublicKeyBuilder builder) {
        return builder.build();
    }
}