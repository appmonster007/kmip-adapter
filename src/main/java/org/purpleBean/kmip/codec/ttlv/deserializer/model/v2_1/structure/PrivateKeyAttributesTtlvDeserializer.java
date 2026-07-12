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
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PrivateKeyAttributesTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrivateKeyAttributes, PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

    public PrivateKeyAttributesTtlvDeserializer() {
        super(PrivateKeyAttributes.kmipTag, PrivateKeyAttributes.encodingType);
    }

    @Override
    protected PrivateKeyAttributes.PrivateKeyAttributesBuilder createBuilder() {
        return PrivateKeyAttributes.builder();
    }

    @Override
    protected void setValue(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.attribute(mapper.readValue(p, KmipAttribute.class));
    }

    @Override
    protected PrivateKeyAttributes build(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder) {
        return builder.build();
    }
}