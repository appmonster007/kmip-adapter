package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcdsaPrivateKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TransparentEcdsaPrivateKey, TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder> {

    public TransparentEcdsaPrivateKeyTtlvDeserializer() {
        super(TransparentEcdsaPrivateKey.kmipTag, TransparentEcdsaPrivateKey.encodingType);
    }

    @Override
    protected TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder createBuilder() {
        return TransparentEcdsaPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(mapper.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdsaPrivateKey build(TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder) {
        return builder.build();
    }
}