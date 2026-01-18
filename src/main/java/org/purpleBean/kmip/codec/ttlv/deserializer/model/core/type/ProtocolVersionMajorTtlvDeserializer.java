package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

public class ProtocolVersionMajorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorTtlvDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}