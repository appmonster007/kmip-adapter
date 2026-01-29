package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentDsaPublicKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TransparentDsaPublicKey, TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder> {

    public TransparentDsaPublicKeyTtlvDeserializer() {
        super(TransparentDsaPublicKey.kmipTag, TransparentDsaPublicKey.encodingType);
    }

    @Override
    protected TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder createBuilder() {
        return TransparentDsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(mapper.readValue(p, G.class));
            case KmipTag.Standard.Y -> builder.y(mapper.readValue(p, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDsaPublicKey build(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder) {
        return builder.build();
    }
}