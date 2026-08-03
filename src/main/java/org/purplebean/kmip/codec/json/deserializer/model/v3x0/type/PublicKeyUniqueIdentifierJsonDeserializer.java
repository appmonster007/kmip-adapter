package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.PublicKeyUniqueIdentifier;

/**
 * JSON deserializer for {@link PublicKeyUniqueIdentifier}.
 */
public class PublicKeyUniqueIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PublicKeyUniqueIdentifier,
        PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link PublicKeyUniqueIdentifierJsonDeserializer}.
   */
  public PublicKeyUniqueIdentifierJsonDeserializer() {
    super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType);
  }

  @Override
  protected PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder createBuilder() {
    return PublicKeyUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PublicKeyUniqueIdentifier build(
      PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
