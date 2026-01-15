package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPublicKey;

import java.io.IOException;

public class TransparentEcdhPublicKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentEcdhPublicKey, TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder> {

    public TransparentEcdhPublicKeyJsonDeserializer() {
        super(TransparentEcdhPublicKey.kmipTag, TransparentEcdhPublicKey.encodingType);
    }

    @Override
    protected TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder createBuilder() {
        return TransparentEcdhPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(ctxt.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcdhPublicKey build(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder) {
        return builder.build();
    }
}