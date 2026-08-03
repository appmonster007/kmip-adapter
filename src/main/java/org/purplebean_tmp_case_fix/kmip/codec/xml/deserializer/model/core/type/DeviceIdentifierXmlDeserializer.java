package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DeviceIdentifier,
        DeviceIdentifier.DeviceIdentifierBuilder> {

  public DeviceIdentifierXmlDeserializer() {
    super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType);
  }

  @Override
  protected DeviceIdentifier.DeviceIdentifierBuilder createBuilder() {
    return DeviceIdentifier.builder();
  }

  @Override
  protected void setValue(DeviceIdentifier.DeviceIdentifierBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected DeviceIdentifier build(DeviceIdentifier.DeviceIdentifierBuilder builder) {
    return builder.build();
  }
}