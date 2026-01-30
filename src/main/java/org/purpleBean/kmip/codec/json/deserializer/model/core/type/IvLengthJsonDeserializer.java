package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.IvLength;

import java.io.IOException;

public class IvLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<IvLength, IvLength.IvLengthBuilder> {

    public IvLengthJsonDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType);
    }

    @Override
    protected IvLength.IvLengthBuilder createBuilder() {
        return IvLength.builder();
    }

    @Override
    protected void setValue(IvLength.IvLengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected IvLength build(IvLength.IvLengthBuilder builder) {
        return builder.build();
    }
}
