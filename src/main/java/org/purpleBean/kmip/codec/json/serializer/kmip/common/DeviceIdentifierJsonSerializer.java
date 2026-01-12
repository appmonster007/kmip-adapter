package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierJsonSerializer extends AbstractKmipJsonSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierJsonSerializer() {
        super(DeviceIdentifier::getValue);
    }
}