package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

import java.io.IOException;

public class SecretDataTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SecretDataType, SecretDataType.SecretDataTypeBuilder> {

    public SecretDataTypeXmlDeserializer() {
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