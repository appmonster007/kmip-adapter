package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.common.structure.TransparentSymmetricKey;

import java.io.IOException;

public class TransparentSymmetricKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<TransparentSymmetricKey, TransparentSymmetricKey.TransparentSymmetricKeyBuilder> {

    public TransparentSymmetricKeyJsonDeserializer() {
        super(TransparentSymmetricKey.kmipTag, TransparentSymmetricKey.encodingType);
    }

    @Override
    protected TransparentSymmetricKey.TransparentSymmetricKeyBuilder createBuilder() {
        return TransparentSymmetricKey.builder();
    }

    @Override
    protected void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
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