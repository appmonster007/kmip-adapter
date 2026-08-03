package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.InteropIdentifier;

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
