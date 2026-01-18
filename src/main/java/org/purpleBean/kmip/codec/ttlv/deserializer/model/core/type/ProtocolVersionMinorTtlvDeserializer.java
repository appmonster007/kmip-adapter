package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class ProtocolVersionMinorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorTtlvDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersionMinor.of(value));
    }
}