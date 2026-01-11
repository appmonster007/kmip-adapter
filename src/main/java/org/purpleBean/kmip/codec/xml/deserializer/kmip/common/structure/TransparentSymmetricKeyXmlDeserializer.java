package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.common.structure.TransparentSymmetricKey;

import java.io.IOException;

public class TransparentSymmetricKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentSymmetricKey> {
    private final KmipTag kmipTag = TransparentSymmetricKey.kmipTag;

    @Override
    public TransparentSymmetricKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, "Invalid Tag for TransparentSymmetricKey");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder = TransparentSymmetricKey.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(TransparentSymmetricKey.class, "Unexpected token: " + p.currentToken());
            }
        }

        TransparentSymmetricKey transparentSymmetricKey = builder.build();

        if (!transparentSymmetricKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, "TransparentSymmetricKey not supported for spec " + spec);
            return null;
        }

        return transparentSymmetricKey;
    }

    private void setValue(
            TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.KEY -> builder.key(ctxt.readValue(p, Key.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}