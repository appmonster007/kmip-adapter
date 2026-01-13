package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberTtlvDeserializer() {
        super(DeviceSerialNumber.kmipTag, DeviceSerialNumber.encodingType, String.class, value -> DeviceSerialNumber.builder().value(value).build());
    }
}