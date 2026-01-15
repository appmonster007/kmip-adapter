package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierXmlSerializer() {
        super(DeviceIdentifier::getValue);
    }
}