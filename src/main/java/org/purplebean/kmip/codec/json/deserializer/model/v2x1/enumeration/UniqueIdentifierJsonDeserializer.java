package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier;

/**
 * JSON deserializer for {@link UniqueIdentifier}.
 */
public class UniqueIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier,
        UniqueIdentifier.UniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link UniqueIdentifierJsonDeserializer}.
   */
  public UniqueIdentifierJsonDeserializer() {
    super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
  }

  @Override
  protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
    return UniqueIdentifier.builder();
  }

  @Override
  protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(UniqueIdentifier.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
    return builder.build();
  }
}