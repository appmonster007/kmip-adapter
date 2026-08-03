package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;

public class AsynchronousCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AsynchronousCapability,
        AsynchronousCapability.AsynchronousCapabilityBuilder> {

  public AsynchronousCapabilityJsonDeserializer() {
    super(AsynchronousCapability.kmipTag, AsynchronousCapability.encodingType);
  }

  @Override
  protected AsynchronousCapability.AsynchronousCapabilityBuilder createBuilder() {
    return AsynchronousCapability.builder();
  }

  @Override
  protected void setValue(AsynchronousCapability.AsynchronousCapabilityBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected AsynchronousCapability build(
      AsynchronousCapability.AsynchronousCapabilityBuilder builder) {
    return builder.build();
  }
}