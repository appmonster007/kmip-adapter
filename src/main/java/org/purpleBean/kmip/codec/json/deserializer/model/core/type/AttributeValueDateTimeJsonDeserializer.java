package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.io.IOException;
import java.time.OffsetDateTime;

public class AttributeValueDateTimeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueDateTime, AttributeValueDateTime.AttributeValueDateTimeBuilder> {

    public AttributeValueDateTimeJsonDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType);
    }

    @Override
    protected AttributeValueDateTime.AttributeValueDateTimeBuilder createBuilder() {
        return AttributeValueDateTime.builder();
    }

    @Override
    protected void setValue(AttributeValueDateTime.AttributeValueDateTimeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected AttributeValueDateTime build(AttributeValueDateTime.AttributeValueDateTimeBuilder builder) {
        return builder.build();
    }
}
