package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierXmlSerializer extends AbstractKmipXmlSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierXmlSerializer() {
        super(DeviceIdentifier::getValue);
    }
}