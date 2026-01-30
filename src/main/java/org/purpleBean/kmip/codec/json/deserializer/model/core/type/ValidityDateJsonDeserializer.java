package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ValidityDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidityDate, ValidityDate.ValidityDateBuilder> {

    public ValidityDateJsonDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType);
    }

    @Override
    protected ValidityDate.ValidityDateBuilder createBuilder() {
        return ValidityDate.builder();
    }

    @Override
    protected void setValue(ValidityDate.ValidityDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected ValidityDate build(ValidityDate.ValidityDateBuilder builder) {
        return builder.build();
    }
}
