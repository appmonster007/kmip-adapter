package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberJsonDeserializer extends AbstractKmipJsonDeserializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberJsonDeserializer() {
        super(DeviceSerialNumber.kmipTag, DeviceSerialNumber.encodingType, String.class, value -> DeviceSerialNumber.builder().value(value).build());
    }
}