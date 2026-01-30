package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

import java.io.IOException;

public class SecretDataTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SecretDataType, SecretDataType.SecretDataTypeBuilder> {

    public SecretDataTypeJsonDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType);
    }

    @Override
    protected SecretDataType.SecretDataTypeBuilder createBuilder() {
        return SecretDataType.builder();
    }

    @Override
    protected void setValue(SecretDataType.SecretDataTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(SecretDataType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected SecretDataType build(SecretDataType.SecretDataTypeBuilder builder) {
        return builder.build();
    }
}
