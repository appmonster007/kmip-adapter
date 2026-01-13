package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPublicKey;

import java.io.IOException;

public class TransparentEcmqvPublicKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentEcmqvPublicKey, TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder> {

    public TransparentEcmqvPublicKeyXmlDeserializer() {
        super(TransparentEcmqvPublicKey.kmipTag);
    }

    @Override
    protected TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder createBuilder() {
        return TransparentEcmqvPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(ctxt.readValue(p, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcmqvPublicKey build(TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder) {
        return builder.build();
    }
}