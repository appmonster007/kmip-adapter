package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

import java.io.IOException;

public class InvocationFieldLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InvocationFieldLength, InvocationFieldLength.InvocationFieldLengthBuilder> {

    public InvocationFieldLengthJsonDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType);
    }

    @Override
    protected InvocationFieldLength.InvocationFieldLengthBuilder createBuilder() {
        return InvocationFieldLength.builder();
    }

    @Override
    protected void setValue(InvocationFieldLength.InvocationFieldLengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected InvocationFieldLength build(InvocationFieldLength.InvocationFieldLengthBuilder builder) {
        return builder.build();
    }
}
