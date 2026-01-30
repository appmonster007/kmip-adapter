package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SecretData;

import java.io.IOException;

public class SecretDataJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SecretData, SecretData.SecretDataBuilder> {

    public SecretDataJsonDeserializer() {
        super(SecretData.kmipTag, SecretData.encodingType);
    }

    @Override
    protected SecretData.SecretDataBuilder createBuilder() {
        return SecretData.builder();
    }

    @Override
    protected void setValue(SecretData.SecretDataBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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