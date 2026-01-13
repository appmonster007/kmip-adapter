package org.purpleBean.kmip.codec.json.deserializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorJsonDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersionMinor.of(value));
    }
}