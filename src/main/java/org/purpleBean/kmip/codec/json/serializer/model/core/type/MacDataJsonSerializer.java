package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.MacData;

import java.nio.ByteBuffer;

public class MacDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MacData, ByteBuffer> {

    public MacDataJsonSerializer() {
        super(MacData::getValue);
    }
}