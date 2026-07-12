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
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CommonAttributesTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CommonAttributes, CommonAttributes.CommonAttributesBuilder> {

    public CommonAttributesTtlvDeserializer() {
        super(CommonAttributes.kmipTag, CommonAttributes.encodingType);
    }

    @Override
    protected CommonAttributes.CommonAttributesBuilder createBuilder() {
        return CommonAttributes.builder();
    }

    @Override
    protected void setValue(CommonAttributes.CommonAttributesBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.attribute(mapper.readValue(p, KmipAttribute.class));
    }

    @Override
    protected CommonAttributes build(CommonAttributes.CommonAttributesBuilder builder) {
        return builder.build();
    }
}