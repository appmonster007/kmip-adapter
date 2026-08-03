package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.AttributeReferenceTag;

/**
 * JSON deserializer for {@link AttributeReferenceTag}.
 */
public class AttributeReferenceTagJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttributeReferenceTag,
        AttributeReferenceTag.AttributeReferenceTagBuilder> {

  /**
   * Constructs a new {@link AttributeReferenceTagJsonDeserializer}.
   */
  public AttributeReferenceTagJsonDeserializer() {
    super(AttributeReferenceTag.kmipTag, AttributeReferenceTag.encodingType);
  }

  @Override
  protected AttributeReferenceTag.AttributeReferenceTagBuilder createBuilder() {
    return AttributeReferenceTag.builder();
  }

  @Override
  protected void setValue(AttributeReferenceTag.AttributeReferenceTagBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.tagDescription(ctxt.readValue(p, String.class));
  }

  @Override
  protected AttributeReferenceTag build(
      AttributeReferenceTag.AttributeReferenceTagBuilder builder) {
    return builder.build();
  }
}
