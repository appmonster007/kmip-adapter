package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPublicKey;
import org.purpleBean.kmip.model.core.type.QString;

import java.io.IOException;

public class TransparentEcdsaPublicKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentEcdsaPublicKey, TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder> {

    public TransparentEcdsaPublicKeyXmlDeserializer() {
        super(TransparentEcdsaPublicKey.kmipTag);
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