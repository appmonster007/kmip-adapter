package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PrivateKey;

import java.io.IOException;

public class PrivateKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<PrivateKey, PrivateKey.PrivateKeyBuilder> {

    public PrivateKeyXmlDeserializer() {
        super(PrivateKey.kmipTag);
    }

    @Override
    protected PrivateKey.PrivateKeyBuilder createBuilder() {
        return PrivateKey.builder();
    }

    @Override
    protected void setValue(PrivateKey.PrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PrivateKey build(PrivateKey.PrivateKeyBuilder builder) {
        return builder.build();
    }
}