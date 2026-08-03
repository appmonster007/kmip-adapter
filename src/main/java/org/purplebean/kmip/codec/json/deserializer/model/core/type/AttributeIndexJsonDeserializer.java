package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttributeIndex;

/**
 * JSON deserializer for {@link AttributeIndex}.
 */
public class AttributeIndexJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttributeIndex, AttributeIndex.AttributeIndexBuilder> {

  /**
   * Constructs a new {@link AttributeIndexJsonDeserializer}.
   */
  public AttributeIndexJsonDeserializer() {
    super(AttributeIndex.kmipTag, AttributeIndex.encodingType);
  }

  @Override
  protected AttributeIndex.AttributeIndexBuilder createBuilder() {
    return AttributeIndex.builder();
  }

  @Override
  protected void setValue(AttributeIndex.AttributeIndexBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected AttributeIndex build(AttributeIndex.AttributeIndexBuilder builder) {
    return builder.build();
  }
}
