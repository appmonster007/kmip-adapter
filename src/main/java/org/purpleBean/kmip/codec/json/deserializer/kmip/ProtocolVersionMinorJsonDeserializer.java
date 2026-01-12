package org.purpleBean.kmip.codec.json.deserializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;

public class ProtocolVersionMinorJsonDeserializer extends AbstractKmipJsonDeserializer<ProtocolVersion.ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorJsonDeserializer() {
        super(ProtocolVersion.ProtocolVersionMinor.kmipTag, ProtocolVersion.ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersion.ProtocolVersionMinor.of(value));
    }
}