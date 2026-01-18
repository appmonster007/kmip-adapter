package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.io.IOException;

public class TransparentRsaPublicKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentRsaPublicKey, TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder> {

    public TransparentRsaPublicKeyJsonDeserializer() {
        super(TransparentRsaPublicKey.kmipTag, TransparentRsaPublicKey.encodingType);
    }

    @Override
    protected TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder createBuilder() {
        return TransparentRsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(ctxt.readValue(p, Modulus.class));
            case KmipTag.Standard.PUBLIC_EXPONENT -> builder.publicExponent(ctxt.readValue(p, PublicExponent.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentRsaPublicKey build(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder) {
        return builder.build();
    }
}