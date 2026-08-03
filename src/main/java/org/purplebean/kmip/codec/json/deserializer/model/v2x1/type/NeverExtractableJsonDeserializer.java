package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.NeverExtractable;

/**
 * JSON deserializer for {@link NeverExtractable}.
 */
public class NeverExtractableJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NeverExtractable,
        NeverExtractable.NeverExtractableBuilder> {

  /**
   * Constructs a new {@link NeverExtractableJsonDeserializer}.
   */
  public NeverExtractableJsonDeserializer() {
    super(NeverExtractable.kmipTag, NeverExtractable.encodingType);
  }

  @Override
  protected NeverExtractable.NeverExtractableBuilder createBuilder() {
    return NeverExtractable.builder();
  }

  @Override
  protected void setValue(NeverExtractable.NeverExtractableBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected NeverExtractable build(NeverExtractable.NeverExtractableBuilder builder) {
    return builder.build();
  }
}