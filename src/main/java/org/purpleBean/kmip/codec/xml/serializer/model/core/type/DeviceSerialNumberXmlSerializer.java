package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceSerialNumberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeviceSerialNumber, String> {

    public DeviceSerialNumberXmlSerializer() {
        super(DeviceSerialNumber::getValue);
    }
}