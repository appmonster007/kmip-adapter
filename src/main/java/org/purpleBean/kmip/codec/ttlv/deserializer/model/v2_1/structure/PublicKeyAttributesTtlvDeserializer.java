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
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PublicKeyAttributesTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PublicKeyAttributes, PublicKeyAttributes.PublicKeyAttributesBuilder> {

    public PublicKeyAttributesTtlvDeserializer() {
        super(PublicKeyAttributes.kmipTag, PublicKeyAttributes.encodingType);
    }

    @Override
    protected PublicKeyAttributes.PublicKeyAttributesBuilder createBuilder() {
        return PublicKeyAttributes.builder();
    }

    @Override
    protected void setValue(PublicKeyAttributes.PublicKeyAttributesBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.attribute(mapper.readValue(p, KmipAttribute.class));
    }

    @Override
    protected PublicKeyAttributes build(PublicKeyAttributes.PublicKeyAttributesBuilder builder) {
        return builder.build();
    }
}