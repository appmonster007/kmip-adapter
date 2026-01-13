package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.common.structure.TransparentSymmetricKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentSymmetricKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentSymmetricKey, TransparentSymmetricKey.TransparentSymmetricKeyBuilder> {

    public TransparentSymmetricKeyTtlvDeserializer() {
        super(TransparentSymmetricKey.kmipTag);
    }

    @Override
    protected TransparentSymmetricKey.TransparentSymmetricKeyBuilder createBuilder() {
        return TransparentSymmetricKey.builder();
    }

    @Override
    protected void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY -> builder.key(mapper.readValue(p, Key.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentSymmetricKey build(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentSymmetricKey.encodingType;
    }
}