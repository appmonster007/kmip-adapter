package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.OtpInterval;

/**
 * TTLV deserializer for {@link OtpInterval}.
 */
public class OtpIntervalTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpInterval, OtpInterval.OtpIntervalBuilder> {

  /**
   * Constructs a new {@link OtpIntervalTtlvDeserializer}.
   */
  public OtpIntervalTtlvDeserializer() {
    super(OtpInterval.kmipTag, OtpInterval.encodingType);
  }

  @Override
  protected OtpInterval.OtpIntervalBuilder createBuilder() {
    return OtpInterval.builder();
  }

  @Override
  protected void setValue(OtpInterval.OtpIntervalBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected OtpInterval build(OtpInterval.OtpIntervalBuilder builder) {
    return builder.build();
  }
}
