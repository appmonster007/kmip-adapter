package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;

public class CompromiseOccurrenceDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CompromiseOccurrenceDate,
        CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder> {

  public CompromiseOccurrenceDateTtlvDeserializer() {
    super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType);
  }

  @Override
  protected CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder createBuilder() {
    return CompromiseOccurrenceDate.builder();
  }

  @Override
  protected void setValue(CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected CompromiseOccurrenceDate build(
      CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder) {
    return builder.build();
  }
}
