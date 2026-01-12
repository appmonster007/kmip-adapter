package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;

public class ProtocolVersionMinorTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProtocolVersion.ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvDeserializer() {
        super(ProtocolVersion.ProtocolVersionMinor.kmipTag, ProtocolVersion.ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersion.ProtocolVersionMinor.of(value));
    }
}