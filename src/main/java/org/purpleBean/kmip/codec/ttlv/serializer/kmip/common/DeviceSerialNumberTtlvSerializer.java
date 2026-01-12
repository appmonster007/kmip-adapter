package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberTtlvSerializer extends AbstractKmipTtlvSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberTtlvSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}