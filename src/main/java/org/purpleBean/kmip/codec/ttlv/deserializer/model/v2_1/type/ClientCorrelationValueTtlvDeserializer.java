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
import org.purpleBean.kmip.model.v2_1.type.ClientCorrelationValue;

import java.io.IOException;

public class ClientCorrelationValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ClientCorrelationValue, ClientCorrelationValue.ClientCorrelationValueBuilder> {

    public ClientCorrelationValueTtlvDeserializer() {
        super(ClientCorrelationValue.kmipTag, ClientCorrelationValue.encodingType);
    }

    @Override
    protected ClientCorrelationValue.ClientCorrelationValueBuilder createBuilder() {
        return ClientCorrelationValue.builder();
    }

    @Override
    protected void setValue(ClientCorrelationValue.ClientCorrelationValueBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected ClientCorrelationValue build(ClientCorrelationValue.ClientCorrelationValueBuilder builder) {
        return builder.build();
    }
}