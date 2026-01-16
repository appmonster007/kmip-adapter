package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SecretData;

import java.io.IOException;

public class SecretDataXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SecretData, SecretData.SecretDataBuilder> {

    public SecretDataXmlDeserializer() {
        super(SecretData.kmipTag);
    }

    @Override
    protected SecretData.SecretDataBuilder createBuilder() {
        return SecretData.builder();
    }

    @Override
    protected void setValue(SecretData.SecretDataBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SECRET_DATA_TYPE -> builder.secretDataType(ctxt.readValue(p, SecretDataType.class));
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SecretData build(SecretData.SecretDataBuilder builder) {
        return builder.build();
    }
}