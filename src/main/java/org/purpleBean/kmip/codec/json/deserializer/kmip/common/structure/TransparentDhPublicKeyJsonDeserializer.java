package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPublicKey;

import java.io.IOException;

public class TransparentDhPublicKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentDhPublicKey, TransparentDhPublicKey.TransparentDhPublicKeyBuilder> {

    public TransparentDhPublicKeyJsonDeserializer() {
        super(TransparentDhPublicKey.kmipTag, TransparentDhPublicKey.encodingType);
    }

    @Override
    protected TransparentDhPublicKey.TransparentDhPublicKeyBuilder createBuilder() {
        return TransparentDhPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentDhPublicKey.TransparentDhPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
            case KmipTag.Standard.J -> builder.j(ctxt.readValue(p, J.class));
            case KmipTag.Standard.Y -> builder.y(ctxt.readValue(p, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDhPublicKey build(TransparentDhPublicKey.TransparentDhPublicKeyBuilder builder) {
        return builder.build();
    }
}