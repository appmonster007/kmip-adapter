package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}