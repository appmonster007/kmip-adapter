package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPrivateKey;

import java.io.IOException;

public class TransparentEcmqvPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentEcmqvPrivateKey> {
    private final KmipTag kmipTag = TransparentEcmqvPrivateKey.kmipTag;

    @Override
    public TransparentEcmqvPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(TransparentEcmqvPrivateKey.class, "Invalid Tag for TransparentEcmqvPrivateKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder = TransparentEcmqvPrivateKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentEcmqvPrivateKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentEcmqvPrivateKey transparentEcmqvPrivateKey = builder.build();

        if (!transparentEcmqvPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentEcmqvPrivateKey.class, "TransparentEcmqvPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentEcmqvPrivateKey;
    }

    private void setValue(
            TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(ctxt.readValue(p, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}