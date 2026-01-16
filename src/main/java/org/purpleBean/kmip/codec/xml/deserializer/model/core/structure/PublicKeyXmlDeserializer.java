package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PublicKey;

import java.io.IOException;

public class PublicKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<PublicKey, PublicKey.PublicKeyBuilder> {

    public PublicKeyXmlDeserializer() {
        super(PublicKey.kmipTag);
    }

    @Override
    protected PublicKey.PublicKeyBuilder createBuilder() {
        return PublicKey.builder();
    }

    @Override
    protected void setValue(PublicKey.PublicKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PublicKey build(PublicKey.PublicKeyBuilder builder) {
        return builder.build();
    }
}