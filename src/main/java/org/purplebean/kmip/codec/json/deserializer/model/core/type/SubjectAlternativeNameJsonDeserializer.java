package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;

/**
 * JSON deserializer for {@link SubjectAlternativeName}.
 */
public class SubjectAlternativeNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SubjectAlternativeName,
        SubjectAlternativeName.SubjectAlternativeNameBuilder> {

  /**
   * Constructs a new {@link SubjectAlternativeNameJsonDeserializer}.
   */
  public SubjectAlternativeNameJsonDeserializer() {
    super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType);
  }

  @Override
  protected SubjectAlternativeName.SubjectAlternativeNameBuilder createBuilder() {
    return SubjectAlternativeName.builder();
  }

  @Override
  protected void setValue(SubjectAlternativeName.SubjectAlternativeNameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SubjectAlternativeName build(
      SubjectAlternativeName.SubjectAlternativeNameBuilder builder) {
    return builder.build();
  }
}
