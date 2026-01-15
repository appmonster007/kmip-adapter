package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentDsaPublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentDsaPublicKey, TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder> {

    public TransparentDsaPublicKeyTtlvDeserializer() {
        super(TransparentDsaPublicKey.kmipTag);
    }

    @Override
    protected TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder createBuilder() {
        return TransparentDsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return TransparentDsaPublicKey.encodingType;
    }
}