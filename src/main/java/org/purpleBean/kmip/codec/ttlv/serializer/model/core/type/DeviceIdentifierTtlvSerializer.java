package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierTtlvSerializer() {
        super(DeviceIdentifier::getValue);
    }
}