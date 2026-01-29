package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectType, ObjectType.ObjectTypeBuilder> {

    public ObjectTypeTtlvDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType);
    }

    @Override
    protected ObjectType.ObjectTypeBuilder createBuilder() {
        return ObjectType.builder();
    }

    @Override
    protected void setValue(ObjectType.ObjectTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ObjectType.fromValue(value));
    }

    @Override
    protected ObjectType build(ObjectType.ObjectTypeBuilder builder) {
        return builder.build();
    }
}
