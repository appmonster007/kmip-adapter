package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersionMinor.of(value));
    }
}