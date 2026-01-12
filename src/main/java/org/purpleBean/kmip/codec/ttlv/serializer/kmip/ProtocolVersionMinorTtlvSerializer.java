package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;

public class ProtocolVersionMinorTtlvSerializer extends AbstractKmipTtlvSerializer<ProtocolVersion.ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvSerializer() {
        super(ProtocolVersion.ProtocolVersionMinor::getValue);
    }
}