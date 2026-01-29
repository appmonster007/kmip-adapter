package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectGroupTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectGroup, ObjectGroup.ObjectGroupBuilder> {

    public ObjectGroupTtlvDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType);
    }

    @Override
    protected ObjectGroup.ObjectGroupBuilder createBuilder() {
        return ObjectGroup.builder();
    }

    @Override
    protected void setValue(ObjectGroup.ObjectGroupBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected ObjectGroup build(ObjectGroup.ObjectGroupBuilder builder) {
        return builder.build();
    }
}
