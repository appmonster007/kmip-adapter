package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.SerialNumber;

import java.io.IOException;

public class SerialNumberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SerialNumber, SerialNumber.SerialNumberBuilder> {

    public SerialNumberJsonDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType);
    }

    @Override
    protected SerialNumber.SerialNumberBuilder createBuilder() {
        return SerialNumber.builder();
    }

    @Override
    protected void setValue(SerialNumber.SerialNumberBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected SerialNumber build(SerialNumber.SerialNumberBuilder builder) {
        return builder.build();
    }
}
