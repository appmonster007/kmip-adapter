package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UsernameAndPasswordTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<UsernameAndPassword, UsernameAndPassword.UsernameAndPasswordBuilder> {

    public UsernameAndPasswordTtlvDeserializer() {
        super(UsernameAndPassword.kmipTag);
    }

    @Override
    protected UsernameAndPassword.UsernameAndPasswordBuilder createBuilder() {
        return UsernameAndPassword.builder();
    }

    @Override
    protected void setValue(UsernameAndPassword.UsernameAndPasswordBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.USERNAME -> builder.username(mapper.readValue(p, Username.class));
            case KmipTag.Standard.PASSWORD -> builder.password(mapper.readValue(p, Password.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected UsernameAndPassword build(UsernameAndPassword.UsernameAndPasswordBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return UsernameAndPassword.encodingType;
    }
}