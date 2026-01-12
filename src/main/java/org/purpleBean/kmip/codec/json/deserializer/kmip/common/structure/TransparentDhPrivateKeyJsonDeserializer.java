package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPrivateKey;

import java.io.IOException;

public class TransparentDhPrivateKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentDhPrivateKey, TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder> {

    public TransparentDhPrivateKeyJsonDeserializer() {
        super(TransparentDhPrivateKey.kmipTag, TransparentDhPrivateKey.encodingType);
    }

    @Override
    protected TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder createBuilder() {
        return TransparentDhPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
            case KmipTag.Standard.J -> builder.j(ctxt.readValue(p, J.class));
            case KmipTag.Standard.X -> builder.x(ctxt.readValue(p, X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDhPrivateKey build(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder) {
        return builder.build();
    }
}