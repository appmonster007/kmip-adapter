package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataLength;

import java.io.IOException;

public class DataLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataLength, DataLength.DataLengthBuilder> {

    public DataLengthJsonDeserializer() {
        super(DataLength.kmipTag, DataLength.encodingType);
    }

    @Override
    protected DataLength.DataLengthBuilder createBuilder() {
        return DataLength.builder();
    }

    @Override
    protected void setValue(DataLength.DataLengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected DataLength build(DataLength.DataLengthBuilder builder) {
        return builder.build();
    }
}
