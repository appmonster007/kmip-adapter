package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberXmlDeserializer extends AbstractKmipXmlDeserializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberXmlDeserializer() {
        super(DeviceSerialNumber.kmipTag, DeviceSerialNumber.encodingType, String.class, value -> DeviceSerialNumber.builder().value(value).build());
    }
}