package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.common.structure.TransparentDsaPrivateKey;

import java.io.IOException;

public class TransparentDsaPrivateKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentDsaPrivateKey, TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder> {

    public TransparentDsaPrivateKeyXmlDeserializer() {
        super(TransparentDsaPrivateKey.kmipTag);
    }

    @Override
    protected TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder createBuilder() {
        return TransparentDsaPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
            case KmipTag.Standard.X -> builder.x(ctxt.readValue(p, X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentDsaPrivateKey build(TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder builder) {
        return builder.build();
    }
}