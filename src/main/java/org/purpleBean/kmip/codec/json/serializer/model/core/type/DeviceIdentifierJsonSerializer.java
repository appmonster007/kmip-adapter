package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierJsonSerializer() {
        super(DeviceIdentifier::getValue);
    }
}