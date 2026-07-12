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
import org.purpleBean.kmip.model.v2_1.structure.ObjectGroups;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectGroupsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectGroups, ObjectGroups.ObjectGroupsBuilder> {

    public ObjectGroupsTtlvDeserializer() {
        super(ObjectGroups.kmipTag, ObjectGroups.encodingType);
    }

    @Override
    protected ObjectGroups.ObjectGroupsBuilder createBuilder() {
        return ObjectGroups.builder();
    }

    @Override
    protected void setValue(ObjectGroups.ObjectGroupsBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_GROUP -> builder.objectGroup(mapper.readValue(p, ObjectGroup.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObjectGroups build(ObjectGroups.ObjectGroupsBuilder builder) {
        return builder.build();
    }
}