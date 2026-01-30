package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Username;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UsernameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Username, Username.UsernameBuilder> {

    public UsernameTtlvDeserializer() {
        super(Username.kmipTag, Username.encodingType);
    }

    @Override
    protected Username.UsernameBuilder createBuilder() {
        return Username.builder();
    }

    @Override
    protected void setValue(Username.UsernameBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected Username build(Username.UsernameBuilder builder) {
        return builder.build();
    }
}