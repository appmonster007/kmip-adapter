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
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.io.IOException;

public class CorrelationValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CorrelationValue, CorrelationValue.CorrelationValueBuilder> {

    public CorrelationValueTtlvDeserializer() {
        super(CorrelationValue.kmipTag, CorrelationValue.encodingType);
    }

    @Override
    protected CorrelationValue.CorrelationValueBuilder createBuilder() {
        return CorrelationValue.builder();
    }

    @Override
    protected void setValue(CorrelationValue.CorrelationValueBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected CorrelationValue build(CorrelationValue.CorrelationValueBuilder builder) {
        return builder.build();
    }
}