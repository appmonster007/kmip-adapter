package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPublicKey;

import java.io.IOException;

public class TransparentEcdsaPublicKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentEcdsaPublicKey, TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder> {

    public TransparentEcdsaPublicKeyJsonDeserializer() {
        super(TransparentEcdsaPublicKey.kmipTag, TransparentEcdsaPublicKey.encodingType);
    }

    @Override
    protected TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder createBuilder() {
        return TransparentEcdsaPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(ctxt.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdsaPublicKey build(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder) {
        return builder.build();
    }
}