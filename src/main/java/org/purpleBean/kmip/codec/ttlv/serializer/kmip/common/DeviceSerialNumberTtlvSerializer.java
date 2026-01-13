package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberTtlvSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}