package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

/**
 * JSON deserializer for {@link PrivateKeyUniqueIdentifier}.
 */
public class PrivateKeyUniqueIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrivateKeyUniqueIdentifier,
        PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link PrivateKeyUniqueIdentifierJsonDeserializer}.
   */
  public PrivateKeyUniqueIdentifierJsonDeserializer() {
    super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType);
  }

  @Override
  protected PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder createBuilder() {
    return PrivateKeyUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PrivateKeyUniqueIdentifier build(
      PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
