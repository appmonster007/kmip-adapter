package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.common.structure.TransparentRsaPublicKey;

import java.io.IOException;

public class TransparentRsaPublicKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentRsaPublicKey> {
    private final KmipTag kmipTag = TransparentRsaPublicKey.kmipTag;

    @Override
    public TransparentRsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class, "Invalid Tag for TransparentRsaPublicKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder = TransparentRsaPublicKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentRsaPublicKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentRsaPublicKey transparentRsaPublicKey = builder.build();

        if (!transparentRsaPublicKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class, "TransparentRsaPublicKey not supported for spec " + spec);
            return null;
        }

        return transparentRsaPublicKey;
    }

    private void setValue(
            TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(ctxt.readValue(p, Modulus.class));
            case KmipTag.Standard.PUBLIC_EXPONENT -> builder.publicExponent(ctxt.readValue(p, PublicExponent.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}