package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.common.structure.TransparentDsaPublicKey;

import java.io.IOException;

public class TransparentDsaPublicKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentDsaPublicKey, TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder> {

    public TransparentDsaPublicKeyXmlDeserializer() {
        super(TransparentDsaPublicKey.kmipTag);
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