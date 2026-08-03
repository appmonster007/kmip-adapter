package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ReplacedUniqueIdentifier,
        ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder> {

  public ReplacedUniqueIdentifierJsonDeserializer() {
    super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType);
  }

  @Override
  protected ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder createBuilder() {
    return ReplacedUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ReplacedUniqueIdentifier build(
      ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
