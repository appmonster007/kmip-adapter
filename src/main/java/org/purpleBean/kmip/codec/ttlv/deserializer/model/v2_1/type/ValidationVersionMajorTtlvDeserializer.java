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
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;

import java.io.IOException;

public class ValidationVersionMajorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationVersionMajor, ValidationVersionMajor.ValidationVersionMajorBuilder> {

    public ValidationVersionMajorTtlvDeserializer() {
        super(ValidationVersionMajor.kmipTag, ValidationVersionMajor.encodingType);
    }

    @Override
    protected ValidationVersionMajor.ValidationVersionMajorBuilder createBuilder() {
        return ValidationVersionMajor.builder();
    }

    @Override
    protected void setValue(ValidationVersionMajor.ValidationVersionMajorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected ValidationVersionMajor build(ValidationVersionMajor.ValidationVersionMajorBuilder builder) {
        return builder.build();
    }
}