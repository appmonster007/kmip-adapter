package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ProfileNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProfileName, ProfileName.ProfileNameBuilder> {

    public ProfileNameTtlvDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType);
    }

    @Override
    protected ProfileName.ProfileNameBuilder createBuilder() {
        return ProfileName.builder();
    }

    @Override
    protected void setValue(ProfileName.ProfileNameBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ProfileName.fromValue(value));
    }

    @Override
    protected ProfileName build(ProfileName.ProfileNameBuilder builder) {
        return builder.build();
    }
}
