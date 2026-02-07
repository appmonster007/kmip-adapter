package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcPublicKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TransparentEcPublicKey, TransparentEcPublicKey.TransparentEcPublicKeyBuilder> {

    public TransparentEcPublicKeyTtlvDeserializer() {
        super(TransparentEcPublicKey.kmipTag, TransparentEcPublicKey.encodingType);
    }

    @Override
    protected TransparentEcPublicKey.TransparentEcPublicKeyBuilder createBuilder() {
        return TransparentEcPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcPublicKey.TransparentEcPublicKeyBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag == RecommendedCurve.kmipTag.getValue()) {
            builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
        } else if (nodeTag == QString.kmipTag.getValue()) {
            builder.qString(mapper.readValue(p, QString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcPublicKey build(TransparentEcPublicKey.TransparentEcPublicKeyBuilder builder) {
        return builder.build();
    }
}