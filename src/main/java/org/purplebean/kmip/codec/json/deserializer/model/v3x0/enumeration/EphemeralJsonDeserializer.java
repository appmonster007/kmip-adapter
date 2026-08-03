package org.purplebean.kmip.codec.json.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.Ephemeral;

/**
 * JSON deserializer for {@link Ephemeral}.
 */
public class EphemeralJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Ephemeral, Ephemeral.EphemeralBuilder> {

  /**
   * Constructs a new {@link EphemeralJsonDeserializer}.
   */
  public EphemeralJsonDeserializer() {
    super(Ephemeral.kmipTag, Ephemeral.encodingType);
  }

  @Override
  protected Ephemeral.EphemeralBuilder createBuilder() {
    return Ephemeral.builder();
  }

  @Override
  protected void setValue(Ephemeral.EphemeralBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(Ephemeral.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
    return builder.build();
  }
}
