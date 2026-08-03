package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.OtpSerial;

public class OtpSerialTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpSerial, OtpSerial.OtpSerialBuilder> {

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
