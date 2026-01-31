package org.purpleBean.kmip.codec.ttlv.deserializer.api;

import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * TTLV deserializer for {@link AttributeValue} objects.
 * <p>
 * This class extends {@link KmipDataTypeTtlvDeserializer} to handle the deserialization
 * of KMIP Attribute Values from TTLV. It relies on the base class logic to identify
 * the correct concrete type based on the tag and encoding type.
 */
public class AttributeValueTtlvDeserializer extends KmipDataTypeTtlvDeserializer<AttributeValue> {

    @Override
    public AttributeValue deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }
}
