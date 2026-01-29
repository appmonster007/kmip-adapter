package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CompromiseOccurrenceDate, CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder> {

    public CompromiseOccurrenceDateTtlvDeserializer() {
        super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType);
    }

    @Override
    protected CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder createBuilder() {
        return CompromiseOccurrenceDate.builder();
    }

    @Override
    protected void setValue(CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected CompromiseOccurrenceDate build(CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder) {
        return builder.build();
    }
}
