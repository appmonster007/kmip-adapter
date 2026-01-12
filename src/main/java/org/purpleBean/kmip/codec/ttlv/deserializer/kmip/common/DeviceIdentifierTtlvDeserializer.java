package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<DeviceIdentifier, String> {

    public DeviceIdentifierTtlvDeserializer() {
        super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType, String.class, value -> DeviceIdentifier.builder().value(value).build());
    }
}