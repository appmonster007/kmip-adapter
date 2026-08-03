package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.SerialNumber;

/**
 * JSON deserializer for {@link SerialNumber}.
 */
public class SerialNumberJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<SerialNumber, SerialNumber.SerialNumberBuilder> {

  /**
   * Constructs a new {@link SerialNumberJsonDeserializer}.
   */
  public SerialNumberJsonDeserializer() {
    super(SerialNumber.kmipTag, SerialNumber.encodingType);
  }

  @Override
  protected SerialNumber.SerialNumberBuilder createBuilder() {
    return SerialNumber.builder();
  }

  @Override
  protected void setValue(SerialNumber.SerialNumberBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected SerialNumber build(SerialNumber.SerialNumberBuilder builder) {
    return builder.build();
  }
}
