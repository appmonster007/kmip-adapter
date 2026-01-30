package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MaximumItems;

import java.io.IOException;

public class MaximumItemsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MaximumItems, MaximumItems.MaximumItemsBuilder> {

    public MaximumItemsJsonDeserializer() {
        super(MaximumItems.kmipTag, MaximumItems.encodingType);
    }

    @Override
    protected MaximumItems.MaximumItemsBuilder createBuilder() {
        return MaximumItems.builder();
    }

    @Override
    protected void setValue(MaximumItems.MaximumItemsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected MaximumItems build(MaximumItems.MaximumItemsBuilder builder) {
        return builder.build();
    }
}
