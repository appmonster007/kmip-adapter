package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MacData, ByteBuffer> {

    public MacDataJsonSerializer() {
        super(MacData::getValue);
    }
}