package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.common.structure.TransparentDsaPrivateKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentDsaPrivateKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentDsaPrivateKey, TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder> {

    public TransparentDsaPrivateKeyTtlvDeserializer() {
        super(TransparentDsaPrivateKey.kmipTag);
    }

    @Override
    protected TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder createBuilder() {
        return TransparentDsaPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(mapper.readValue(p, G.class));
            case KmipTag.Standard.X -> builder.x(mapper.readValue(p, X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDsaPrivateKey build(TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentDsaPrivateKey.encodingType;
    }
}