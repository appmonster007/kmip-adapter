package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;

public class ProtocolVersionMajorTtlvSerializer extends AbstractKmipTtlvSerializer<ProtocolVersion.ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvSerializer() {
        super(ProtocolVersion.ProtocolVersionMajor::getValue);
    }
}