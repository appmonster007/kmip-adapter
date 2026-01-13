package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MacData, ByteBuffer> {

    public MacDataJsonDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}