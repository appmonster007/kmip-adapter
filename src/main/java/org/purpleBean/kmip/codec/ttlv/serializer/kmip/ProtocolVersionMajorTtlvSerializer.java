package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}