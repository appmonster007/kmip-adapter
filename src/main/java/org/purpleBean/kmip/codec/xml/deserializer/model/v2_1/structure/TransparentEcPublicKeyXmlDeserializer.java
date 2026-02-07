package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPublicKey;

import java.io.IOException;

public class TransparentEcPublicKeyXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TransparentEcPublicKey, TransparentEcPublicKey.TransparentEcPublicKeyBuilder> {

    public TransparentEcPublicKeyXmlDeserializer() {
        super(TransparentEcPublicKey.kmipTag, TransparentEcPublicKey.encodingType);
    }

    @Override
    protected TransparentEcPublicKey.TransparentEcPublicKeyBuilder createBuilder() {
        return TransparentEcPublicKey.builder();
    }

    @Override
    protected void setValue(TransparentEcPublicKey.TransparentEcPublicKeyBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag == RecommendedCurve.kmipTag.getValue()) {
            builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
        } else if (nodeTag == QString.kmipTag.getValue()) {
            builder.qString(ctxt.readValue(p, QString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentEcPublicKey build(TransparentEcPublicKey.TransparentEcPublicKeyBuilder builder) {
        return builder.build();
    }
}