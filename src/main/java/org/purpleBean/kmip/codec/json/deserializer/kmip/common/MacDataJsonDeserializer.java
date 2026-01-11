package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataJsonDeserializer extends AbstractKmipJsonDeserializer<MacData, ByteBuffer> {

    public MacDataJsonDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}