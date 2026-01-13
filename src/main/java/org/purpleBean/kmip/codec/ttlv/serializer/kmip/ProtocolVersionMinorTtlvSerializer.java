package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}