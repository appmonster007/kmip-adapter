package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.QuantumSafeCapability;

/**
 * JSON deserializer for {@link QuantumSafeCapability}.
 */
public class QuantumSafeCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<QuantumSafeCapability,
        QuantumSafeCapability.QuantumSafeCapabilityBuilder> {

  /**
   * Constructs a new {@link QuantumSafeCapabilityJsonDeserializer}.
   */
  public QuantumSafeCapabilityJsonDeserializer() {
    super(QuantumSafeCapability.kmipTag, QuantumSafeCapability.encodingType);
  }

  @Override
  protected QuantumSafeCapability.QuantumSafeCapabilityBuilder createBuilder() {
    return QuantumSafeCapability.builder();
  }

  @Override
  protected void setValue(QuantumSafeCapability.QuantumSafeCapabilityBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected QuantumSafeCapability build(
      QuantumSafeCapability.QuantumSafeCapabilityBuilder builder) {
    return builder.build();
  }
}