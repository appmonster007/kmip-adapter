package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class DestroyDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DestroyDate, DestroyDate.DestroyDateBuilder> {

    public DestroyDateJsonDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType);
    }

    @Override
    protected DestroyDate.DestroyDateBuilder createBuilder() {
        return DestroyDate.builder();
    }

    @Override
    protected void setValue(DestroyDate.DestroyDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected DestroyDate build(DestroyDate.DestroyDateBuilder builder) {
        return builder.build();
    }
}
