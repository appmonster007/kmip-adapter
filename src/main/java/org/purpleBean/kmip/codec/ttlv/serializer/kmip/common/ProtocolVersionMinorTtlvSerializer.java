package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}