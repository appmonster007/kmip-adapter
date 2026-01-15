package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Key;
import org.purpleBean.kmip.model.core.structure.TransparentSymmetricKey;

import java.io.IOException;

public class TransparentSymmetricKeyXmlDeserializer extends AbstractKmipStructureXmlDeserializer<TransparentSymmetricKey, TransparentSymmetricKey.TransparentSymmetricKeyBuilder> {

    public TransparentSymmetricKeyXmlDeserializer() {
        super(TransparentSymmetricKey.kmipTag);
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