package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.Constraint;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ConstraintTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Constraint, Constraint.ConstraintBuilder> {

    public ConstraintTtlvDeserializer() {
        super(Constraint.kmipTag, Constraint.encodingType);
    }

    @Override
    protected Constraint.ConstraintBuilder createBuilder() {
        return Constraint.builder();
    }

    @Override
    protected void setValue(Constraint.ConstraintBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected Constraint build(Constraint.ConstraintBuilder builder) {
        return builder.build();
    }
}