package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.QuantumSafe;

/**
 * XML deserializer for {@link QuantumSafe}.
 */
public class QuantumSafeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<QuantumSafe, QuantumSafe.QuantumSafeBuilder> {

  /**
   * Constructs a new {@link QuantumSafeXmlDeserializer}.
   */
  public QuantumSafeXmlDeserializer() {
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