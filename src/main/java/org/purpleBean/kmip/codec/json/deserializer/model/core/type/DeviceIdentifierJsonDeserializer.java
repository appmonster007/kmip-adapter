package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeviceIdentifier, String> {

    public DeviceIdentifierJsonDeserializer() {
        super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType, String.class, value -> DeviceIdentifier.builder().value(value).build());
    }
}