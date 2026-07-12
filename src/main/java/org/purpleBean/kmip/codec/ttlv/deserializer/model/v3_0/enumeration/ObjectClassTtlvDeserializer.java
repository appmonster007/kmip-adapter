package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.enumeration.ObjectClass;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectClassTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectClass, ObjectClass.ObjectClassBuilder> {

    public ObjectClassTtlvDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType);
    }

    @Override
    protected ObjectClass.ObjectClassBuilder createBuilder() {
        return ObjectClass.builder();
    }

    @Override
    protected void setValue(ObjectClass.ObjectClassBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ObjectClass.fromValue(value));
    }

    @Override
    protected ObjectClass build(ObjectClass.ObjectClassBuilder builder) {
        return builder.build();
    }
}
