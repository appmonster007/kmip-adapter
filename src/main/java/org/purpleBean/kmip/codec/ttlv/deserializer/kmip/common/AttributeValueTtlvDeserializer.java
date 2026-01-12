package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueTtlvDeserializer extends KmipDataTypeTtlvDeserializer<AttributeValue> {

    @Override
    public AttributeValue deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return (AttributeValue) super.deserialize(ttlvBuffer, mapper);
    }
}