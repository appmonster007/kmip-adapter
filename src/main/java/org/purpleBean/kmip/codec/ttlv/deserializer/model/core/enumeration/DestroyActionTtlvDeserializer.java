package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DestroyActionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DestroyAction, DestroyAction.DestroyActionBuilder> {

    public DestroyActionTtlvDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType);
    }

    @Override
    protected DestroyAction.DestroyActionBuilder createBuilder() {
        return DestroyAction.builder();
    }

    @Override
    protected void setValue(DestroyAction.DestroyActionBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(DestroyAction.fromValue(value));
    }

    @Override
    protected DestroyAction build(DestroyAction.DestroyActionBuilder builder) {
        return builder.build();
    }
}
