package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPrivateKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.X;

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