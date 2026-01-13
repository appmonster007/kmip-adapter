package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcdsaPublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentEcdsaPublicKey, TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder> {

    public TransparentEcdsaPublicKeyTtlvDeserializer() {
        super(TransparentEcdsaPublicKey.kmipTag);
    }

    @Override
    protected TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder createBuilder() {
        return TransparentEcdsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(mapper.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdsaPublicKey build(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentEcdsaPublicKey.encodingType;
    }
}