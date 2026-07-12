package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.Right;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.structure.Operations;
import org.purpleBean.kmip.model.v2_1.structure.ObjectGroups;

public class RightJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Right, Right.RightBuilder> {

    public RightJsonDeserializer() {
        super(Right.kmipTag, Right.encodingType);
    }

    @Override
    protected Right.RightBuilder createBuilder() {
        return Right.builder();
    }

    @Override
    protected void setValue(Right.RightBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS -> builder.usageLimits(ctxt.readValue(p, UsageLimits.class));
            case KmipTag.Standard.OPERATIONS -> builder.operations(ctxt.readValue(p, Operations.class));
            case KmipTag.Standard.OBJECTS -> builder.managedObjects(ctxt.readValue(p, org.purpleBean.kmip.model.v2_1.structure.Objects.class));
            case KmipTag.Standard.OBJECT_GROUPS -> builder.objectGroups(ctxt.readValue(p, ObjectGroups.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Right build(Right.RightBuilder builder) {
        return builder.build();
    }
}