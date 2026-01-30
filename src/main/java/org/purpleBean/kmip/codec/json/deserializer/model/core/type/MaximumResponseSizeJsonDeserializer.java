package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

import java.io.IOException;

public class MaximumResponseSizeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MaximumResponseSize, MaximumResponseSize.MaximumResponseSizeBuilder> {

    public MaximumResponseSizeJsonDeserializer() {
        super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType);
    }

    @Override
    protected MaximumResponseSize.MaximumResponseSizeBuilder createBuilder() {
        return MaximumResponseSize.builder();
    }

    @Override
    protected void setValue(MaximumResponseSize.MaximumResponseSizeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected MaximumResponseSize build(MaximumResponseSize.MaximumResponseSizeBuilder builder) {
        return builder.build();
    }
}
