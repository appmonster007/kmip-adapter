package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

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
import org.purpleBean.kmip.model.core.structure.ObjectDefaults;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectDefaultsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectDefaults, ObjectDefaults.ObjectDefaultsBuilder> {

    public ObjectDefaultsTtlvDeserializer() {
        super(ObjectDefaults.kmipTag, ObjectDefaults.encodingType);
    }

    @Override
    protected ObjectDefaults.ObjectDefaultsBuilder createBuilder() {
        return ObjectDefaults.builder();
    }

    @Override
    protected void setValue(ObjectDefaults.ObjectDefaultsBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObjectDefaults build(ObjectDefaults.ObjectDefaultsBuilder builder) {
        return builder.build();
    }
}