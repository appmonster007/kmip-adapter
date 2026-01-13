package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeviceIdentifier, String> {

    public DeviceIdentifierXmlDeserializer() {
        super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType, String.class, value -> DeviceIdentifier.builder().value(value).build());
    }
}