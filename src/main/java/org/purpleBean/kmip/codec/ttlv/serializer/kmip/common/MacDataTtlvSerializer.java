package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MacData, ByteBuffer> {

    public MacDataTtlvSerializer() {
        super(MacData::getValue);
    }
}