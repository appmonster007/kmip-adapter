package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.Description;

/**
 * JSON deserializer for {@link Description}.
 */
public class DescriptionJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Description, Description.DescriptionBuilder> {

  /**
   * Constructs a new {@link DescriptionJsonDeserializer}.
   */
  public DescriptionJsonDeserializer() {
    super(Description.kmipTag, Description.encodingType);
  }

  @Override
  protected Description.DescriptionBuilder createBuilder() {
    return Description.builder();
  }

  @Override
  protected void setValue(Description.DescriptionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Description build(Description.DescriptionBuilder builder) {
    return builder.build();
  }
}