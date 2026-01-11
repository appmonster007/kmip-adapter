package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.KeyMaterial;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyMaterialTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KeyMaterial> {

    @Override
    public KeyMaterial deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return (KeyMaterial) super.deserialize(ttlvBuffer, mapper);
    }
}