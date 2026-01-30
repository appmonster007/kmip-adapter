package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPublicKey;
import org.purpleBean.kmip.model.core.type.QString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentEcdhPublicKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TransparentEcdhPublicKey, TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder> {

    public TransparentEcdhPublicKeyTtlvDeserializer() {
        super(TransparentEcdhPublicKey.kmipTag, TransparentEcdhPublicKey.encodingType);
    }

    @Override
    protected TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder createBuilder() {
        return TransparentEcdhPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
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
}