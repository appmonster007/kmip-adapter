package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorTtlvSerializer extends AbstractKmipTtlvSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}