package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataJsonSerializer extends AbstractKmipJsonSerializer<MacData, ByteBuffer> {

    public MacDataJsonSerializer() {
        super(MacData::getValue);
    }
}