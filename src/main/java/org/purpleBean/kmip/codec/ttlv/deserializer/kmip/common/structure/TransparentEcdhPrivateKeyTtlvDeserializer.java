package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPrivateKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcdhPrivateKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentEcdhPrivateKey, TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder> {

    public TransparentEcdhPrivateKeyTtlvDeserializer() {
        super(TransparentEcdhPrivateKey.kmipTag);
    }

    @Override
    protected TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder createBuilder() {
        return TransparentEcdhPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(mapper.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdhPrivateKey build(TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentEcdhPrivateKey.encodingType;
    }
}