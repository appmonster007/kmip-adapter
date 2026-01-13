package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcdhPublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentEcdhPublicKey, TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder> {

    public TransparentEcdhPublicKeyTtlvDeserializer() {
        super(TransparentEcdhPublicKey.kmipTag);
    }

    @Override
    protected TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder createBuilder() {
        return TransparentEcdhPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(mapper.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdhPublicKey build(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentEcdhPublicKey.encodingType;
    }
}