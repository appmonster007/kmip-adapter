package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DataByteStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataByteString, DataByteString.DataByteStringBuilder> {

    public DataByteStringJsonDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType);
    }

    @Override
    protected DataByteString.DataByteStringBuilder createBuilder() {
        return DataByteString.builder();
    }

    @Override
    protected void setValue(DataByteString.DataByteStringBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected DataByteString build(DataByteString.DataByteStringBuilder builder) {
        return builder.build();
    }
}
