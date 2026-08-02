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
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2_1.structure.RotateName;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RotateNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RotateName, RotateName.RotateNameBuilder> {

    public RotateNameTtlvDeserializer() {
        super(RotateName.kmipTag, RotateName.encodingType);
    }

    @Override
    protected RotateName.RotateNameBuilder createBuilder() {
        return RotateName.builder();
    }

    @Override
    protected void setValue(RotateName.RotateNameBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ROTATE_NAME_VALUE -> builder.rotateNameValue(mapper.readValue(p, RotateNameValue.class));
            case KmipTag.Standard.ROTATE_NAME_TYPE -> builder.rotateNameType(mapper.readValue(p, RotateNameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RotateName build(RotateName.RotateNameBuilder builder) {
        return builder.build();
    }
}