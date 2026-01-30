package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class OriginalCreationDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OriginalCreationDate, OriginalCreationDate.OriginalCreationDateBuilder> {

    public OriginalCreationDateJsonDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType);
    }

    @Override
    protected OriginalCreationDate.OriginalCreationDateBuilder createBuilder() {
        return OriginalCreationDate.builder();
    }

    @Override
    protected void setValue(OriginalCreationDate.OriginalCreationDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected OriginalCreationDate build(OriginalCreationDate.OriginalCreationDateBuilder builder) {
        return builder.build();
    }
}
