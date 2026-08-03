package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;

/**
 * JSON deserializer for {@link LinkedObjectIdentifier}.
 */
public class LinkedObjectIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<LinkedObjectIdentifier,
        LinkedObjectIdentifier.LinkedObjectIdentifierBuilder> {

  /**
   * Constructs a new {@link LinkedObjectIdentifierJsonDeserializer}.
   */
  public LinkedObjectIdentifierJsonDeserializer() {
    super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType);
  }

  @Override
  protected LinkedObjectIdentifier.LinkedObjectIdentifierBuilder createBuilder() {
    return LinkedObjectIdentifier.builder();
  }

  @Override
  protected void setValue(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected LinkedObjectIdentifier build(
      LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder) {
    return builder.build();
  }
}
