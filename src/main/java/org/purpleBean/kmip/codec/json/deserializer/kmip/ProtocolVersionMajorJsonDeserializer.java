package org.purpleBean.kmip.codec.json.deserializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;

public class ProtocolVersionMajorJsonDeserializer extends AbstractKmipJsonDeserializer<ProtocolVersion.ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorJsonDeserializer() {
        super(ProtocolVersion.ProtocolVersionMajor.kmipTag, ProtocolVersion.ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersion.ProtocolVersionMajor.of(value));
    }
}