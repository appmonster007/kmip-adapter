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
import org.purpleBean.kmip.model.v2_1.structure.Right;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.structure.Operations;
import org.purpleBean.kmip.model.v2_1.structure.ObjectGroups;

public class RightTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Right, Right.RightBuilder> {

    public RightTtlvDeserializer() {
        super(Right.kmipTag, Right.encodingType);
    }

    @Override
    protected Right.RightBuilder createBuilder() {
        return Right.builder();
    }

    @Override
    protected void setValue(Right.RightBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS -> builder.usageLimits(mapper.readValue(p, UsageLimits.class));
            case KmipTag.Standard.OPERATIONS -> builder.operations(mapper.readValue(p, Operations.class));
            case KmipTag.Standard.OBJECTS -> builder.managedObjects(mapper.readValue(p, org.purpleBean.kmip.model.v2_1.structure.Objects.class));
            case KmipTag.Standard.OBJECT_GROUPS -> builder.objectGroups(mapper.readValue(p, ObjectGroups.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Right build(Right.RightBuilder builder) {
        return builder.build();
    }
}