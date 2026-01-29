package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

import java.io.IOException;
import java.nio.ByteBuffer;

public class EndpointRoleTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<EndpointRole, EndpointRole.EndpointRoleBuilder> {

    public EndpointRoleTtlvDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType);
    }

    @Override
    protected EndpointRole.EndpointRoleBuilder createBuilder() {
        return EndpointRole.builder();
    }

    @Override
    protected void setValue(EndpointRole.EndpointRoleBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(EndpointRole.fromValue(value));
    }

    @Override
    protected EndpointRole build(EndpointRole.EndpointRoleBuilder builder) {
        return builder.build();
    }
}
