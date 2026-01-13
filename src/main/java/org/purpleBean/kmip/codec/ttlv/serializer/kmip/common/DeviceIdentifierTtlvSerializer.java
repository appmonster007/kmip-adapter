package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DeviceIdentifier, String> {

    public DeviceIdentifierTtlvSerializer() {
        super(DeviceIdentifier::getValue);
    }
}