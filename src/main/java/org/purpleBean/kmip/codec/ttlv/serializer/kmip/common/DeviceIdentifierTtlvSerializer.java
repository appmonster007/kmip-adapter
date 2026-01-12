package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierTtlvSerializer() {
        super(DeviceIdentifier::getValue);
    }
}