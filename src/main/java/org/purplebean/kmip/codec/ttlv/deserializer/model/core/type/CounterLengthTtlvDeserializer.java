package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CounterLength;

public class CounterLengthTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CounterLength, CounterLength.CounterLengthBuilder> {

  public CounterLengthTtlvDeserializer() {
    super(CounterLength.kmipTag, CounterLength.encodingType);
  }

  @Override
  protected CounterLength.CounterLengthBuilder createBuilder() {
    return CounterLength.builder();
  }

  @Override
  protected void setValue(CounterLength.CounterLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected CounterLength build(CounterLength.CounterLengthBuilder builder) {
    return builder.build();
  }
}
