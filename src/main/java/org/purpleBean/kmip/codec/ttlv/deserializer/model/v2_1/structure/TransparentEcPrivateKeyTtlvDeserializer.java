package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPrivateKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcPrivateKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TransparentEcPrivateKey, TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder> {

    public TransparentEcPrivateKeyTtlvDeserializer() {
        super(TransparentEcPrivateKey.kmipTag, TransparentEcPrivateKey.encodingType);
    }

    @Override
    protected TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder createBuilder() {
        return TransparentEcPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag == RecommendedCurve.kmipTag.getValue()) {
            builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
        } else if (nodeTag == D.kmipTag.getValue()) {
            builder.d(mapper.readValue(p, D.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcPrivateKey build(TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder builder) {
        return builder.build();
    }
}