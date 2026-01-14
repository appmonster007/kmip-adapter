package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}