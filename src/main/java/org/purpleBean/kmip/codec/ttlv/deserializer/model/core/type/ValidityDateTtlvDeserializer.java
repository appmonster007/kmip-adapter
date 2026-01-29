package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ValidityDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidityDate, ValidityDate.ValidityDateBuilder> {

    public ValidityDateTtlvDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType);
    }

    @Override
    protected ValidityDate.ValidityDateBuilder createBuilder() {
        return ValidityDate.builder();
    }

    @Override
    protected void setValue(ValidityDate.ValidityDateBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected ValidityDate build(ValidityDate.ValidityDateBuilder builder) {
        return builder.build();
    }
}