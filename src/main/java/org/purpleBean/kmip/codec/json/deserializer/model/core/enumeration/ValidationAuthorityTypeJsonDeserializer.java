package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

import java.io.IOException;

public class ValidationAuthorityTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidationAuthorityType, ValidationAuthorityType.ValidationAuthorityTypeBuilder> {

    public ValidationAuthorityTypeJsonDeserializer() {
        super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType);
    }

    @Override
    protected ValidationAuthorityType.ValidationAuthorityTypeBuilder createBuilder() {
        return ValidationAuthorityType.builder();
    }

    @Override
    protected void setValue(ValidationAuthorityType.ValidationAuthorityTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ValidationAuthorityType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ValidationAuthorityType build(ValidationAuthorityType.ValidationAuthorityTypeBuilder builder) {
        return builder.build();
    }
}
