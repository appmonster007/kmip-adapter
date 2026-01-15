package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.MacData;

import java.nio.ByteBuffer;

public class MacDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MacData, ByteBuffer> {

    public MacDataTtlvSerializer() {
        super(MacData::getValue);
    }
}