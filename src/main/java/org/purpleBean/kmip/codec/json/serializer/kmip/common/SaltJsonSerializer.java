package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Salt, ByteBuffer> {

    public SaltJsonSerializer() {
        super(Salt::getValue);
    }
}