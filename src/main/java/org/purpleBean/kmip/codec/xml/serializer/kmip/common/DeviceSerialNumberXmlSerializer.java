package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DeviceSerialNumber;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DeviceSerialNumberXmlSerializer extends AbstractKmipXmlSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberXmlSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}