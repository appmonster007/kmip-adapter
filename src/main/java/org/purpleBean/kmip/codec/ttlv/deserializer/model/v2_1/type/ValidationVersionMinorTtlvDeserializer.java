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
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMinor;

import java.io.IOException;

public class ValidationVersionMinorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationVersionMinor, ValidationVersionMinor.ValidationVersionMinorBuilder> {

    public ValidationVersionMinorTtlvDeserializer() {
        super(ValidationVersionMinor.kmipTag, ValidationVersionMinor.encodingType);
    }

    @Override
    protected ValidationVersionMinor.ValidationVersionMinorBuilder createBuilder() {
        return ValidationVersionMinor.builder();
    }

    @Override
    protected void setValue(ValidationVersionMinor.ValidationVersionMinorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected ValidationVersionMinor build(ValidationVersionMinor.ValidationVersionMinorBuilder builder) {
        return builder.build();
    }
}