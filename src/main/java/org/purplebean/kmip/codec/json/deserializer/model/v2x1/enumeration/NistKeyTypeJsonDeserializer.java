package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.NistKeyType;

/**
 * JSON deserializer for {@link NistKeyType}.
 */
public class NistKeyTypeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<NistKeyType, NistKeyType.NistKeyTypeBuilder> {

  /**
   * Constructs a new {@link NistKeyTypeJsonDeserializer}.
   */
  public NistKeyTypeJsonDeserializer() {
    super(NistKeyType.kmipTag, NistKeyType.encodingType);
  }

  @Override
  protected NistKeyType.NistKeyTypeBuilder createBuilder() {
    return NistKeyType.builder();
  }

  @Override
  protected void setValue(NistKeyType.NistKeyTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(NistKeyType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected NistKeyType build(NistKeyType.NistKeyTypeBuilder builder) {
    return builder.build();
  }
}
