package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberXmlSerializer extends AbstractKmipXmlSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberXmlSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}