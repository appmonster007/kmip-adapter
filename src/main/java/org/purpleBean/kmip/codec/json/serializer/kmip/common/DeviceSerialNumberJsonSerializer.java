package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberJsonSerializer extends AbstractKmipJsonSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberJsonSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}