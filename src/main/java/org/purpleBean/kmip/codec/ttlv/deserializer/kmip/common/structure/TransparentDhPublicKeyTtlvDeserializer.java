package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentDhPublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentDhPublicKey, TransparentDhPublicKey.TransparentDhPublicKeyBuilder> {

    public TransparentDhPublicKeyTtlvDeserializer() {
        super(TransparentDhPublicKey.kmipTag);
    }

    @Override
    protected TransparentDhPublicKey.TransparentDhPublicKeyBuilder createBuilder() {
        return TransparentDhPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentDhPublicKey.TransparentDhPublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(mapper.readValue(p, G.class));
            case KmipTag.Standard.J -> builder.j(mapper.readValue(p, J.class));
            case KmipTag.Standard.Y -> builder.y(mapper.readValue(p, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDhPublicKey build(TransparentDhPublicKey.TransparentDhPublicKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentDhPublicKey.encodingType;
    }
}