package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierJsonSerializer() {
        super(DeviceIdentifier::getValue);
    }
}