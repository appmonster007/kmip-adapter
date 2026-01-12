package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPrivateKey;

import java.io.IOException;

public class TransparentEcmqvPrivateKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentEcmqvPrivateKey, TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder> {

    public TransparentEcmqvPrivateKeyJsonDeserializer() {
        super(TransparentEcmqvPrivateKey.kmipTag, TransparentEcmqvPrivateKey.encodingType);
    }

    @Override
    protected TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder createBuilder() {
        return TransparentEcmqvPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(ctxt.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcmqvPrivateKey build(TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder) {
        return builder.build();
    }
}