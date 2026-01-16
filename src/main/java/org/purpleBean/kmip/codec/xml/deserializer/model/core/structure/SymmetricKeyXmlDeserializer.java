package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;

import java.io.IOException;

public class SymmetricKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SymmetricKey, SymmetricKey.SymmetricKeyBuilder> {

    public SymmetricKeyXmlDeserializer() {
        super(SymmetricKey.kmipTag);
    }

    @Override
    protected SymmetricKey.SymmetricKeyBuilder createBuilder() {
        return SymmetricKey.builder();
    }

    @Override
    protected void setValue(SymmetricKey.SymmetricKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
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