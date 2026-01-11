package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.io.IOException;

public class TransparentRsaPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentRsaPrivateKey> {
    private final KmipTag kmipTag = TransparentRsaPrivateKey.kmipTag;

    @Override
    public TransparentRsaPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "Invalid Tag for TransparentRsaPrivateKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder = TransparentRsaPrivateKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentRsaPrivateKey transparentRsaPrivateKey = builder.build();

        if (!transparentRsaPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "TransparentRsaPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentRsaPrivateKey;
    }

    private void setValue(
            TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
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
}