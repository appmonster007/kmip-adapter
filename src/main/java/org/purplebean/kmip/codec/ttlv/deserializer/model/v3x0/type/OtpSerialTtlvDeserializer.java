package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.OtpSerial;

/**
 * TTLV deserializer for {@link OtpSerial}.
 */
public class OtpSerialTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpSerial, OtpSerial.OtpSerialBuilder> {

  /**
   * Constructs a new {@link OtpSerialTtlvDeserializer}.
   */
  public OtpSerialTtlvDeserializer() {
    super(OtpSerial.kmipTag, OtpSerial.encodingType);
  }

  @Override
  protected OtpSerial.OtpSerialBuilder createBuilder() {
    return OtpSerial.builder();
  }

  @Override
  protected void setValue(OtpSerial.OtpSerialBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected OtpSerial build(OtpSerial.OtpSerialBuilder builder) {
    return builder.build();
  }
}
