package org.purpleBean.kmip.codec.json.serializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;

public class ProtocolVersionMajorJsonSerializer extends AbstractKmipJsonSerializer<ProtocolVersion.ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorJsonSerializer() {
        super(ProtocolVersion.ProtocolVersionMajor::getValue);
    }
}