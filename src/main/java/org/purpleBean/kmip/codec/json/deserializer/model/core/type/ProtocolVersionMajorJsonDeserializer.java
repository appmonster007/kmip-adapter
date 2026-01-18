package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

public class ProtocolVersionMajorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorJsonDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}