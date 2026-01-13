package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersionMinor.of(value));
    }
}