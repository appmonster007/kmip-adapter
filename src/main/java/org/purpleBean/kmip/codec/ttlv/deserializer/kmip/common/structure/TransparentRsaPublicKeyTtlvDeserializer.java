package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.common.structure.TransparentRsaPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentRsaPublicKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentRsaPublicKey, TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder> {

    public TransparentRsaPublicKeyTtlvDeserializer() {
        super(TransparentRsaPublicKey.kmipTag);
    }

    @Override
    protected TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder createBuilder() {
        return TransparentRsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(mapper.readValue(p, Modulus.class));
            case KmipTag.Standard.PUBLIC_EXPONENT -> builder.publicExponent(mapper.readValue(p, PublicExponent.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentRsaPublicKey build(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentRsaPublicKey.encodingType;
    }
}