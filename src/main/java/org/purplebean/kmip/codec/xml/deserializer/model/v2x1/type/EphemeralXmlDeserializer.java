package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;

/**
 * XML deserializer for {@link Ephemeral}.
 */
public class EphemeralXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Ephemeral, Ephemeral.EphemeralBuilder> {

  /**
   * Constructs a new {@link EphemeralXmlDeserializer}.
   */
  public EphemeralXmlDeserializer() {
    super(Ephemeral.kmipTag, Ephemeral.encodingType);
  }

  @Override
  protected Ephemeral.EphemeralBuilder createBuilder() {
    return Ephemeral.builder();
  }

  @Override
  protected void setValue(Ephemeral.EphemeralBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
    return builder.build();
  }
}