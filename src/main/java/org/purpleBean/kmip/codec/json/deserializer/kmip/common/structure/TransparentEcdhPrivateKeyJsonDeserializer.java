package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPrivateKey;

import java.io.IOException;

public class TransparentEcdhPrivateKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentEcdhPrivateKey, TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder> {

    public TransparentEcdhPrivateKeyJsonDeserializer() {
        super(TransparentEcdhPrivateKey.kmipTag, TransparentEcdhPrivateKey.encodingType);
    }

    @Override
    protected TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder createBuilder() {
        return TransparentEcdhPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(ctxt.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdhPrivateKey build(TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder) {
        return builder.build();
    }
}