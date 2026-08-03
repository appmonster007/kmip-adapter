package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.QuantumSafe;

/**
 * JSON deserializer for {@link QuantumSafe}.
 */
public class QuantumSafeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<QuantumSafe, QuantumSafe.QuantumSafeBuilder> {

  /**
   * Constructs a new {@link QuantumSafeJsonDeserializer}.
   */
  public QuantumSafeJsonDeserializer() {
    super(QuantumSafe.kmipTag, QuantumSafe.encodingType);
  }

  @Override
  protected QuantumSafe.QuantumSafeBuilder createBuilder() {
    return QuantumSafe.builder();
  }

  @Override
  protected void setValue(QuantumSafe.QuantumSafeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected QuantumSafe build(QuantumSafe.QuantumSafeBuilder builder) {
    return builder.build();
  }
}