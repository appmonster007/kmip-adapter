package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purpleBean.kmip.model.core.type.*;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransparentRsaPrivateKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<TransparentRsaPrivateKey, TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder> {

    public TransparentRsaPrivateKeyTtlvDeserializer() {
        super(TransparentRsaPrivateKey.kmipTag);
    }

    @Override
    protected TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder createBuilder() {
        return TransparentRsaPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(mapper.readValue(p, Modulus.class));
            case KmipTag.Standard.PRIVATE_EXPONENT ->
                    builder.privateExponent(mapper.readValue(p, PrivateExponent.class));
            case KmipTag.Standard.PUBLIC_EXPONENT -> builder.publicExponent(mapper.readValue(p, PublicExponent.class));
            case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
            case KmipTag.Standard.PRIME_EXPONENT_P -> builder.primeExponentP(mapper.readValue(p, PrimeExponentP.class));
            case KmipTag.Standard.PRIME_EXPONENT_Q -> builder.primeExponentQ(mapper.readValue(p, PrimeExponentQ.class));
            case KmipTag.Standard.CRT_COEFFICIENT -> builder.crtCoefficient(mapper.readValue(p, CRTCoefficient.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentRsaPrivateKey build(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return TransparentRsaPrivateKey.encodingType;
    }
}