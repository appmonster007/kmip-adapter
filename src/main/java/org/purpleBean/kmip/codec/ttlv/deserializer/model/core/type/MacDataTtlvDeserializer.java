package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.MacData;

import java.nio.ByteBuffer;

public class MacDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MacData, ByteBuffer> {

    public MacDataTtlvDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}