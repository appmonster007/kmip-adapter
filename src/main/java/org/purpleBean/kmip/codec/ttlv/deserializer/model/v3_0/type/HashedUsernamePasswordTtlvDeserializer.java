package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.HashedUsernamePassword;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HashedUsernamePasswordTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<HashedUsernamePassword, HashedUsernamePassword.HashedUsernamePasswordBuilder> {

    public HashedUsernamePasswordTtlvDeserializer() {
        super(HashedUsernamePassword.kmipTag, HashedUsernamePassword.encodingType);
    }

    @Override
    protected HashedUsernamePassword.HashedUsernamePasswordBuilder createBuilder() {
        return HashedUsernamePassword.builder();
    }

    @Override
    protected void setValue(HashedUsernamePassword.HashedUsernamePasswordBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected HashedUsernamePassword build(HashedUsernamePassword.HashedUsernamePasswordBuilder builder) {
        return builder.build();
    }
}
