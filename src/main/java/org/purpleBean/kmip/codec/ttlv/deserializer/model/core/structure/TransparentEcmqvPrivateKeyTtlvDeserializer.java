package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcmqvPrivateKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentEcmqvPrivateKey, TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder> {

    public TransparentEcmqvPrivateKeyTtlvDeserializer() {
        super(TransparentEcmqvPrivateKey.kmipTag);
    }

    @Override
    protected TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder createBuilder() {
        return TransparentEcmqvPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(mapper.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcmqvPrivateKey build(TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentEcmqvPrivateKey.encodingType;
    }
}