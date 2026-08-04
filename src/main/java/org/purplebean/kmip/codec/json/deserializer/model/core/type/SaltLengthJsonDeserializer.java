package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.SaltLength;

/**
 * JSON deserializer for {@link SaltLength}.
 */
public class SaltLengthJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<SaltLength, SaltLength.SaltLengthBuilder> {

  /**
   * Constructs a new {@link SaltLengthJsonDeserializer}.
   */
  public SaltLengthJsonDeserializer() {
    super(SaltLength.kmipTag, SaltLength.encodingType);
  }

  @Override
  protected SaltLength.SaltLengthBuilder createBuilder() {
    return SaltLength.builder();
  }

  @Override
  protected void setValue(SaltLength.SaltLengthBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected SaltLength build(SaltLength.SaltLengthBuilder builder) {
    return builder.build();
  }
}
