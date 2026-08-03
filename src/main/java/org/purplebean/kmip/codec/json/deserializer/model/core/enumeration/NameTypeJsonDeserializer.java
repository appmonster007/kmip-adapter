package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.NameType;

/**
 * JSON deserializer for {@link NameType}.
 */
public class NameTypeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<NameType, NameType.NameTypeBuilder> {

  /**
   * Constructs a new {@link NameTypeJsonDeserializer}.
   */
  public NameTypeJsonDeserializer() {
    super(NameType.kmipTag, NameType.encodingType);
  }

  @Override
  protected NameType.NameTypeBuilder createBuilder() {
    return NameType.builder();
  }

  @Override
  protected void setValue(NameType.NameTypeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(NameType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected NameType build(NameType.NameTypeBuilder builder) {
    return builder.build();
  }
}
