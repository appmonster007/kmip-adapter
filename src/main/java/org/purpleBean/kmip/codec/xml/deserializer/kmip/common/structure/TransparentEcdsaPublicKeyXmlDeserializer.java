package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPublicKey;

import java.io.IOException;

public class TransparentEcdsaPublicKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentEcdsaPublicKey> {
    private final KmipTag kmipTag = TransparentEcdsaPublicKey.kmipTag;

    @Override
    public TransparentEcdsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, "Invalid Tag for TransparentEcdsaPublicKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder = TransparentEcdsaPublicKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentEcdsaPublicKey transparentEcdsaPublicKey = builder.build();

        if (!transparentEcdsaPublicKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, "TransparentEcdsaPublicKey not supported for spec " + spec);
            return null;
        }

        return transparentEcdsaPublicKey;
    }

    private void setValue(
            TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(ctxt.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}