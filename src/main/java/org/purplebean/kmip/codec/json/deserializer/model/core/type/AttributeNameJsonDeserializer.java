package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttributeName;

/**
 * JSON deserializer for {@link AttributeName}.
 */
public class AttributeNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttributeName, AttributeName.AttributeNameBuilder> {

  /**
   * Constructs a new {@link AttributeNameJsonDeserializer}.
   */
  public AttributeNameJsonDeserializer() {
    super(AttributeName.kmipTag, AttributeName.encodingType);
  }

  @Override
  protected AttributeName.AttributeNameBuilder createBuilder() {
    return AttributeName.builder();
  }

  @Override
  protected void setValue(AttributeName.AttributeNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected AttributeName build(AttributeName.AttributeNameBuilder builder) {
    return builder.build();
  }
}
