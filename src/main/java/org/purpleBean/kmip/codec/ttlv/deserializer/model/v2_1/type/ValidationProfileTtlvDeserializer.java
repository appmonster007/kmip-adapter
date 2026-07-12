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
import org.purpleBean.kmip.model.v2_1.type.ValidationProfile;

import java.io.IOException;

public class ValidationProfileTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationProfile, ValidationProfile.ValidationProfileBuilder> {

    public ValidationProfileTtlvDeserializer() {
        super(ValidationProfile.kmipTag, ValidationProfile.encodingType);
    }

    @Override
    protected ValidationProfile.ValidationProfileBuilder createBuilder() {
        return ValidationProfile.builder();
    }

    @Override
    protected void setValue(ValidationProfile.ValidationProfileBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected ValidationProfile build(ValidationProfile.ValidationProfileBuilder builder) {
        return builder.build();
    }
}