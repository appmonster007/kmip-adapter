package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceSerialNumberXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DeviceSerialNumber,
        DeviceSerialNumber.DeviceSerialNumberBuilder> {

  public DeviceSerialNumberXmlDeserializer() {
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