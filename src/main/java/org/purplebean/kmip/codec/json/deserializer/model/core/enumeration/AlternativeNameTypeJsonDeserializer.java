package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;

/**
 * JSON deserializer for {@link AlternativeNameType}.
 */
public class AlternativeNameTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AlternativeNameType,
        AlternativeNameType.AlternativeNameTypeBuilder> {

  /**
   * Constructs a new {@link AlternativeNameTypeJsonDeserializer}.
   */
  public AlternativeNameTypeJsonDeserializer() {
    super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType);
  }

  @Override
  protected AlternativeNameType.AlternativeNameTypeBuilder createBuilder() {
    return AlternativeNameType.builder();
  }

  @Override
  protected void setValue(AlternativeNameType.AlternativeNameTypeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(AlternativeNameType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected AlternativeNameType build(AlternativeNameType.AlternativeNameTypeBuilder builder) {
    return builder.build();
  }
}
