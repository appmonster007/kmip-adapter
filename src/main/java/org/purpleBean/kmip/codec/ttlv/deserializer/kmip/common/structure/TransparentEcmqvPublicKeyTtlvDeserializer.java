package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcmqvPublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentEcmqvPublicKey, TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder> {

    public TransparentEcmqvPublicKeyTtlvDeserializer() {
        super(TransparentEcmqvPublicKey.kmipTag);
    }

    @Override
    protected TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder createBuilder() {
        return TransparentEcmqvPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(mapper.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcmqvPublicKey build(TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentEcmqvPublicKey.encodingType;
    }
}