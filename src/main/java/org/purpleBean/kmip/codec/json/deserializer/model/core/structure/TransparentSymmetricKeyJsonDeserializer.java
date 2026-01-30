package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.TransparentSymmetricKey;
import org.purpleBean.kmip.model.core.type.Key;

import java.io.IOException;

public class TransparentSymmetricKeyJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TransparentSymmetricKey, TransparentSymmetricKey.TransparentSymmetricKeyBuilder> {

    public TransparentSymmetricKeyJsonDeserializer() {
        super(TransparentSymmetricKey.kmipTag, TransparentSymmetricKey.encodingType);
    }

    @Override
    protected TransparentSymmetricKey.TransparentSymmetricKeyBuilder createBuilder() {
        return TransparentSymmetricKey.builder();
    }

    @Override
    protected void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.KEY -> builder.key(ctxt.readValue(p, Key.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TransparentSymmetricKey build(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder) {
        return builder.build();
    }
}