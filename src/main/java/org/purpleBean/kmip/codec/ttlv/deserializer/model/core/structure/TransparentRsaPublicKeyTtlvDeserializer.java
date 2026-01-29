package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentRsaPublicKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TransparentRsaPublicKey, TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder> {

    public TransparentRsaPublicKeyTtlvDeserializer() {
        super(TransparentRsaPublicKey.kmipTag, TransparentRsaPublicKey.encodingType);
    }

    @Override
    protected TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder createBuilder() {
        return TransparentRsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}