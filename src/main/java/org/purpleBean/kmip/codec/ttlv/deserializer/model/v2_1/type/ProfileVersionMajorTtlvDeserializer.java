package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;

import java.io.IOException;

public class ProfileVersionMajorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProfileVersionMajor, ProfileVersionMajor.ProfileVersionMajorBuilder> {

    public ProfileVersionMajorTtlvDeserializer() {
        super(ProfileVersionMajor.kmipTag, ProfileVersionMajor.encodingType);
    }

    @Override
    protected ProfileVersionMajor.ProfileVersionMajorBuilder createBuilder() {
        return ProfileVersionMajor.builder();
    }

    @Override
    protected void setValue(ProfileVersionMajor.ProfileVersionMajorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected ProfileVersionMajor build(ProfileVersionMajor.ProfileVersionMajorBuilder builder) {
        return builder.build();
    }
}