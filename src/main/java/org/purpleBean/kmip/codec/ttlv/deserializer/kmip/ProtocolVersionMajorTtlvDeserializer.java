package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;

public class ProtocolVersionMajorTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProtocolVersion.ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvDeserializer() {
        super(ProtocolVersion.ProtocolVersionMajor.kmipTag, ProtocolVersion.ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersion.ProtocolVersionMajor.of(value));
    }
}