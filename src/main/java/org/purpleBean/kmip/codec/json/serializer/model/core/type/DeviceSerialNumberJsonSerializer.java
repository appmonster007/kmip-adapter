package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceSerialNumberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberJsonSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}