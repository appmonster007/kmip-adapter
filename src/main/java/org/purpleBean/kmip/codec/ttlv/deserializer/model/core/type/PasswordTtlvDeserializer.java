package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Password;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PasswordTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Password, Password.PasswordBuilder> {

    public PasswordTtlvDeserializer() {
        super(Password.kmipTag, Password.encodingType);
    }

    @Override
    protected Password.PasswordBuilder createBuilder() {
        return Password.builder();
    }

    @Override
    protected void setValue(Password.PasswordBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected Password build(Password.PasswordBuilder builder) {
        return builder.build();
    }
}