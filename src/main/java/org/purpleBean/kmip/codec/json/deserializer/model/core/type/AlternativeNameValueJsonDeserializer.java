package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

import java.io.IOException;

public class AlternativeNameValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AlternativeNameValue, AlternativeNameValue.AlternativeNameValueBuilder> {

    public AlternativeNameValueJsonDeserializer() {
        super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType);
    }

    @Override
    protected AlternativeNameValue.AlternativeNameValueBuilder createBuilder() {
        return AlternativeNameValue.builder();
    }

    @Override
    protected void setValue(AlternativeNameValue.AlternativeNameValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = ctxt.readValue(p, String.class);
        builder.value(value);
    }

    @Override
    protected AlternativeNameValue build(AlternativeNameValue.AlternativeNameValueBuilder builder) {
        return builder.build();
    }
}