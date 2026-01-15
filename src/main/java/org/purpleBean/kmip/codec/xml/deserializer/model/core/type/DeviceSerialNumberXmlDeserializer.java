package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceSerialNumberXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberXmlDeserializer() {
        super(DeviceSerialNumber.kmipTag, DeviceSerialNumber.encodingType, String.class, value -> DeviceSerialNumber.builder().value(value).build());
    }
}