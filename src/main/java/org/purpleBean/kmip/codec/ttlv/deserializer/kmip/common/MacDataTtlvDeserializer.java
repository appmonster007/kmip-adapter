package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataTtlvDeserializer extends AbstractKmipTtlvDeserializer<MacData, ByteBuffer> {

    public MacDataTtlvDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}