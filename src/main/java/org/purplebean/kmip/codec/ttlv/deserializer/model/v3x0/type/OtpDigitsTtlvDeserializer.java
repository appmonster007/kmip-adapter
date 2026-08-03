package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.OtpDigits;

/**
 * TTLV deserializer for {@link OtpDigits}.
 */
public class OtpDigitsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpDigits, OtpDigits.OtpDigitsBuilder> {

  /**
   * Constructs a new {@link OtpDigitsTtlvDeserializer}.
   */
  public OtpDigitsTtlvDeserializer() {
    super(OtpDigits.kmipTag, OtpDigits.encodingType);
  }

  @Override
  protected OtpDigits.OtpDigitsBuilder createBuilder() {
    return OtpDigits.builder();
  }

  @Override
  protected void setValue(OtpDigits.OtpDigitsBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected OtpDigits build(OtpDigits.OtpDigitsBuilder builder) {
    return builder.build();
  }
}
