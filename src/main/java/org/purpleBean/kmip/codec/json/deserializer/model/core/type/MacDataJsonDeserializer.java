package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MacData;

import java.nio.ByteBuffer;

public class MacDataJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MacData, ByteBuffer> {

    public MacDataJsonDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}