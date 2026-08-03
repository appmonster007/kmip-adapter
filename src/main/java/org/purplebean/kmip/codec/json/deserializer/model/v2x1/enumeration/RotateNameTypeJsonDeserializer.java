package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;

/**
 * JSON deserializer for {@link RotateNameType}.
 */
public class RotateNameTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RotateNameType, RotateNameType.RotateNameTypeBuilder> {

  /**
   * Constructs a new {@link RotateNameTypeJsonDeserializer}.
   */
  public RotateNameTypeJsonDeserializer() {
    super(RotateNameType.kmipTag, RotateNameType.encodingType);
  }

  @Override
  protected RotateNameType.RotateNameTypeBuilder createBuilder() {
    return RotateNameType.builder();
  }

  @Override
  protected void setValue(RotateNameType.RotateNameTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(RotateNameType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RotateNameType build(RotateNameType.RotateNameTypeBuilder builder) {
    return builder.build();
  }
}
