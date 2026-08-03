package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpSerial;

/**
 * JSON deserializer for {@link OtpSerial}.
 */
public class OtpSerialJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OtpSerial, OtpSerial.OtpSerialBuilder> {

  /**
   * Constructs a new {@link OtpSerialJsonDeserializer}.
   */
  public OtpSerialJsonDeserializer() {
    super(OtpSerial.kmipTag, OtpSerial.encodingType);
  }

  @Override
  protected OtpSerial.OtpSerialBuilder createBuilder() {
    return OtpSerial.builder();
  }

  @Override
  protected void setValue(OtpSerial.OtpSerialBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected OtpSerial build(OtpSerial.OtpSerialBuilder builder) {
    return builder.build();
  }
}
