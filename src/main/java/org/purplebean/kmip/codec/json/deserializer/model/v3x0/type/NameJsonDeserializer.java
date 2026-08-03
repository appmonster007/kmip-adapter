package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.Name;

/**
 * JSON deserializer for {@link Name}.
 */
public class NameJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Name, Name.NameBuilder> {

  /**
   * Constructs a new {@link NameJsonDeserializer}.
   */
  public NameJsonDeserializer() {
    super(Name.kmipTag, Name.encodingType);
  }

  @Override
  protected Name.NameBuilder createBuilder() {
    return Name.builder();
  }

  @Override
  protected void setValue(Name.NameBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Name build(Name.NameBuilder builder) {
    return builder.build();
  }
}