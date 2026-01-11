package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.common.structure.TransparentDsaPrivateKey;

import java.io.IOException;

public class TransparentDsaPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentDsaPrivateKey> {
    private final KmipTag kmipTag = TransparentDsaPrivateKey.kmipTag;

    @Override
    public TransparentDsaPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(TransparentDsaPrivateKey.class, "Invalid Tag for TransparentDsaPrivateKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder builder = TransparentDsaPrivateKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentDsaPrivateKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentDsaPrivateKey transparentDsaPrivateKey = builder.build();

        if (!transparentDsaPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentDsaPrivateKey.class, "TransparentDsaPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentDsaPrivateKey;
    }

    private void setValue(
            TransparentDsaPrivateKey.TransparentDsaPrivateKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
            case KmipTag.Standard.X -> builder.x(ctxt.readValue(p, X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}