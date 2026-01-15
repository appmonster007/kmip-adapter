package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPrivateKey;

import java.io.IOException;

public class TransparentEcmqvPrivateKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentEcmqvPrivateKey, TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder> {

    public TransparentEcmqvPrivateKeyXmlDeserializer() {
        super(TransparentEcmqvPrivateKey.kmipTag);
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