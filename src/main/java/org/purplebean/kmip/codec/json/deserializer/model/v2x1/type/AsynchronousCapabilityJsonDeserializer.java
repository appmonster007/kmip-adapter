package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.AsynchronousCapability;

/**
 * JSON deserializer for {@link AsynchronousCapability}.
 */
public class AsynchronousCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AsynchronousCapability,
        AsynchronousCapability.AsynchronousCapabilityBuilder> {

  /**
   * Constructs a new {@link AsynchronousCapabilityJsonDeserializer}.
   */
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