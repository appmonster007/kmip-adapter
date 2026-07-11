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
import org.purpleBean.kmip.model.core.structure.Attributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributesTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Attributes, Attributes.AttributesBuilder> {

    public AttributesTtlvDeserializer() {
        super(Attributes.kmipTag, Attributes.encodingType);
    }

    @Override
    protected Attributes.AttributesBuilder createBuilder() {
        return Attributes.builder();
    }

    @Override
    protected void setValue(Attributes.AttributesBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.attribute(mapper.readValue(p, KmipAttribute.class));
    }

    @Override
    protected Attributes build(Attributes.AttributesBuilder builder) {
        return builder.build();
    }
}