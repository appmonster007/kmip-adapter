package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

import java.io.IOException;

public class BatchOrderOptionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<BatchOrderOption, BatchOrderOption.BatchOrderOptionBuilder> {

    public BatchOrderOptionJsonDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType);
    }

    @Override
    protected BatchOrderOption.BatchOrderOptionBuilder createBuilder() {
        return BatchOrderOption.builder();
    }

    @Override
    protected void setValue(BatchOrderOption.BatchOrderOptionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected BatchOrderOption build(BatchOrderOption.BatchOrderOptionBuilder builder) {
        return builder.build();
    }
}
