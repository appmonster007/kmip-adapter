package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.Description;

import java.io.IOException;

public class DescriptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Description, Description.DescriptionBuilder> {

    public DescriptionTtlvDeserializer() {
        super(Description.kmipTag, Description.encodingType);
    }

    @Override
    protected Description.DescriptionBuilder createBuilder() {
        return Description.builder();
    }

    @Override
    protected void setValue(Description.DescriptionBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected Description build(Description.DescriptionBuilder builder) {
        return builder.build();
    }
}