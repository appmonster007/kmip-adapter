package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeviceIdentifier, String> {

    public DeviceIdentifierTtlvDeserializer() {
        super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType, String.class, value -> DeviceIdentifier.builder().value(value).build());
    }
}