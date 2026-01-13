package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberJsonSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}