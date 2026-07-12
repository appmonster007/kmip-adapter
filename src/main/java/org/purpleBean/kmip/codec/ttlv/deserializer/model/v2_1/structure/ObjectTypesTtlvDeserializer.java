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
import org.purpleBean.kmip.model.v2_1.structure.ObjectTypes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectTypesTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectTypes, ObjectTypes.ObjectTypesBuilder> {

    public ObjectTypesTtlvDeserializer() {
        super(ObjectTypes.kmipTag, ObjectTypes.encodingType);
    }

    @Override
    protected ObjectTypes.ObjectTypesBuilder createBuilder() {
        return ObjectTypes.builder();
    }

    @Override
    protected void setValue(ObjectTypes.ObjectTypesBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObjectTypes build(ObjectTypes.ObjectTypesBuilder builder) {
        return builder.build();
    }
}