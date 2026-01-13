package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorTtlvSerializer extends AbstractKmipTtlvSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}