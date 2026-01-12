package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPrivateKey;

import java.io.IOException;

public class TransparentEcdsaPrivateKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentEcdsaPrivateKey, TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder> {

    public TransparentEcdsaPrivateKeyXmlDeserializer() {
        super(TransparentEcdsaPrivateKey.kmipTag);
    }

    @Override
    protected TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder createBuilder() {
        return TransparentEcdsaPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(ctxt.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdsaPrivateKey build(TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder) {
        return builder.build();
    }
}