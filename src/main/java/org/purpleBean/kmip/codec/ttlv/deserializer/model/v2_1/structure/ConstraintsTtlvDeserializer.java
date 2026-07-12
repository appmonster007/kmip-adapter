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
import org.purpleBean.kmip.model.v2_1.structure.Constraints;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.structure.Constraint;

public class ConstraintsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Constraints, Constraints.ConstraintsBuilder> {

    public ConstraintsTtlvDeserializer() {
        super(Constraints.kmipTag, Constraints.encodingType);
    }

    @Override
    protected Constraints.ConstraintsBuilder createBuilder() {
        return Constraints.builder();
    }

    @Override
    protected void setValue(Constraints.ConstraintsBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.CONSTRAINT -> builder.constraint(mapper.readValue(p, Constraint.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Constraints build(Constraints.ConstraintsBuilder builder) {
        return builder.build();
    }
}