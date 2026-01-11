package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPrivateKey;

import java.io.IOException;

public class TransparentEcdsaPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentEcdsaPrivateKey> {
    private final KmipTag kmipTag = TransparentEcdsaPrivateKey.kmipTag;

    @Override
    public TransparentEcdsaPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(TransparentEcdsaPrivateKey.class, "Invalid Tag for TransparentEcdsaPrivateKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder = TransparentEcdsaPrivateKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentEcdsaPrivateKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentEcdsaPrivateKey transparentEcdsaPrivateKey = builder.build();

        if (!transparentEcdsaPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentEcdsaPrivateKey.class, "TransparentEcdsaPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentEcdsaPrivateKey;
    }

    private void setValue(
            TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(ctxt.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}