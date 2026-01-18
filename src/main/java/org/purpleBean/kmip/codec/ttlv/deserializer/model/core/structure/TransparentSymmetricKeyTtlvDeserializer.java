package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TransparentSymmetricKey;
import org.purpleBean.kmip.model.core.type.Key;

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