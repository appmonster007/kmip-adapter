package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberXmlSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}