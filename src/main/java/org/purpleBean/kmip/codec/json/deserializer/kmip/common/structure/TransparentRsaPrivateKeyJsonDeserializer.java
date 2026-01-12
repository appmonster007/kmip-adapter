package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.io.IOException;

public class TransparentRsaPrivateKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentRsaPrivateKey, TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder> {

    public TransparentRsaPrivateKeyJsonDeserializer() {
        super(TransparentRsaPrivateKey.kmipTag, TransparentRsaPrivateKey.encodingType);
    }

    @Override
    protected TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder createBuilder() {
        return TransparentRsaPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(ctxt.readValue(p, Modulus.class));
            case KmipTag.Standard.PRIVATE_EXPONENT -> builder.privateExponent(ctxt.readValue(p, PrivateExponent.class));
            case KmipTag.Standard.PUBLIC_EXPONENT -> builder.publicExponent(ctxt.readValue(p, PublicExponent.class));
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.PRIME_EXPONENT_P -> builder.primeExponentP(ctxt.readValue(p, PrimeExponentP.class));
            case KmipTag.Standard.PRIME_EXPONENT_Q -> builder.primeExponentQ(ctxt.readValue(p, PrimeExponentQ.class));
            case KmipTag.Standard.CRT_COEFFICIENT -> builder.crtCoefficient(ctxt.readValue(p, CRTCoefficient.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentRsaPrivateKey build(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder) {
        return builder.build();
    }
}