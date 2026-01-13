package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierXmlSerializer() {
        super(DeviceIdentifier::getValue);
    }
}