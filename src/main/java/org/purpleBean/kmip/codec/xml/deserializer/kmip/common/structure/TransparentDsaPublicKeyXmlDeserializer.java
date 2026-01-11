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
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.common.structure.TransparentDsaPublicKey;

import java.io.IOException;

public class TransparentDsaPublicKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentDsaPublicKey> {
    private final KmipTag kmipTag = TransparentDsaPublicKey.kmipTag;

    @Override
    public TransparentDsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "Invalid Tag for TransparentDsaPublicKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder = TransparentDsaPublicKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentDsaPublicKey transparentDsaPublicKey = builder.build();

        if (!transparentDsaPublicKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "TransparentDsaPublicKey not supported for spec " + spec);
            return null;
        }

        return transparentDsaPublicKey;
    }

    private void setValue(
            TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
            case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
            case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
            case KmipTag.Standard.Y -> builder.y(ctxt.readValue(p, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}