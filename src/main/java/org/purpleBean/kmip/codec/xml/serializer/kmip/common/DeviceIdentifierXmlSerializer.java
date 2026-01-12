package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DeviceIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DeviceIdentifierXmlSerializer extends AbstractKmipXmlSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierXmlSerializer() {
        super(DeviceIdentifier::getValue);
    }
}