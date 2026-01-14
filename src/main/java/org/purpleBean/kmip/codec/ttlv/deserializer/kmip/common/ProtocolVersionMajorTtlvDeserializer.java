package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}