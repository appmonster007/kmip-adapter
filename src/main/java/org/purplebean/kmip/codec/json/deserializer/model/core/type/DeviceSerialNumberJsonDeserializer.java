package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;

/**
 * JSON deserializer for {@link DeviceSerialNumber}.
 */
public class DeviceSerialNumberJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeviceSerialNumber,
        DeviceSerialNumber.DeviceSerialNumberBuilder> {

  /**
   * Constructs a new {@link DeviceSerialNumberJsonDeserializer}.
   */
  public DeviceSerialNumberJsonDeserializer() {
    super(DeviceSerialNumber.kmipTag, DeviceSerialNumber.encodingType);
  }

  @Override
  protected DeviceSerialNumber.DeviceSerialNumberBuilder createBuilder() {
    return DeviceSerialNumber.builder();
  }

  @Override
  protected void setValue(DeviceSerialNumber.DeviceSerialNumberBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected DeviceSerialNumber build(DeviceSerialNumber.DeviceSerialNumberBuilder builder) {
    return builder.build();
  }
}
