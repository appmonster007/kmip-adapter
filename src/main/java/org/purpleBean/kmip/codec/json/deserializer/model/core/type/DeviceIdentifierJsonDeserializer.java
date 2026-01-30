package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

import java.io.IOException;

public class DeviceIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeviceIdentifier, DeviceIdentifier.DeviceIdentifierBuilder> {

    public DeviceIdentifierJsonDeserializer() {
        super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType);
    }

    @Override
    protected DeviceIdentifier.DeviceIdentifierBuilder createBuilder() {
        return DeviceIdentifier.builder();
    }

    @Override
    protected void setValue(DeviceIdentifier.DeviceIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected DeviceIdentifier build(DeviceIdentifier.DeviceIdentifierBuilder builder) {
        return builder.build();
    }
}
