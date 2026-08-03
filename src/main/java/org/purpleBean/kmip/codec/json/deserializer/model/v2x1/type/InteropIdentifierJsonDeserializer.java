package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

public class InteropIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<InteropIdentifier,
        InteropIdentifier.InteropIdentifierBuilder> {

  public InteropIdentifierJsonDeserializer() {
    super(InteropIdentifier.kmipTag, InteropIdentifier.encodingType);
  }

  @Override
  protected InteropIdentifier.InteropIdentifierBuilder createBuilder() {
    return InteropIdentifier.builder();
  }

  @Override
  protected void setValue(InteropIdentifier.InteropIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected InteropIdentifier build(InteropIdentifier.InteropIdentifierBuilder builder) {
    return builder.build();
  }
}
