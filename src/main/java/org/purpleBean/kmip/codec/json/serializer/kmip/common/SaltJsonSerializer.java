package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltJsonSerializer extends AbstractKmipJsonSerializer<Salt, ByteBuffer> {

    public SaltJsonSerializer() {
        super(Salt::getValue);
    }
}