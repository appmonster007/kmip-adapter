package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPublicKey;

import java.io.IOException;

public class TransparentDsaPublicKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentDsaPublicKey, TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder> {

    public TransparentDsaPublicKeyJsonDeserializer() {
        super(TransparentDsaPublicKey.kmipTag, TransparentDsaPublicKey.encodingType);
    }

    @Override
    protected TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder createBuilder() {
        return TransparentDsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
            case KmipTag.Standard.Y -> builder.y(ctxt.readValue(p, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDsaPublicKey build(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder) {
        return builder.build();
    }
}