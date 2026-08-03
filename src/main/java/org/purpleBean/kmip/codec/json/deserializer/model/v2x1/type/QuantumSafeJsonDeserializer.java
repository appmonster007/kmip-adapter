package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.QuantumSafe;

public class QuantumSafeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<QuantumSafe, QuantumSafe.QuantumSafeBuilder> {

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