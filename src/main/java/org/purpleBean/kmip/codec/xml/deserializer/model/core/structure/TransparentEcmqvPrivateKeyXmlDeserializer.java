package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

import java.io.IOException;

public class TransparentEcmqvPrivateKeyXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TransparentEcmqvPrivateKey, TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder> {

    public TransparentEcmqvPrivateKeyXmlDeserializer() {
        super(TransparentEcmqvPrivateKey.kmipTag, TransparentEcmqvPrivateKey.encodingType);
    }

    @Override
    protected TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder createBuilder() {
        return TransparentEcmqvPrivateKey.builder();
    }

    @Override
    protected void setValue(TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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