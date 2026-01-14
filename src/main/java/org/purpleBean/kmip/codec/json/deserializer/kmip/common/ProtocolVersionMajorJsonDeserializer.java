package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorJsonDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}