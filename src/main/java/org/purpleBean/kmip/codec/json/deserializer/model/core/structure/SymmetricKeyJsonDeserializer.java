package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;

import java.io.IOException;

public class SymmetricKeyJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SymmetricKey, SymmetricKey.SymmetricKeyBuilder> {

    public SymmetricKeyJsonDeserializer() {
        super(SymmetricKey.kmipTag, SymmetricKey.encodingType);
    }

    @Override
    protected SymmetricKey.SymmetricKeyBuilder createBuilder() {
        return SymmetricKey.builder();
    }

    @Override
    protected void setValue(SymmetricKey.SymmetricKeyBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SymmetricKey build(SymmetricKey.SymmetricKeyBuilder builder) {
        return builder.build();
    }
}