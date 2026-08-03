package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.OtpCounter;

public class OtpCounterTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpCounter, OtpCounter.OtpCounterBuilder> {

  public OtpCounterTtlvDeserializer() {
    super(OtpCounter.kmipTag, OtpCounter.encodingType);
  }

  @Override
  protected OtpCounter.OtpCounterBuilder createBuilder() {
    return OtpCounter.builder();
  }

  @Override
  protected void setValue(OtpCounter.OtpCounterBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected OtpCounter build(OtpCounter.OtpCounterBuilder builder) {
    return builder.build();
  }
}
