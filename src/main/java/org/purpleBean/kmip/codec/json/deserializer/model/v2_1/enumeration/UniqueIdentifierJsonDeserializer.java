package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.UniqueIdentifier;

public class UniqueIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier,
        UniqueIdentifier.UniqueIdentifierBuilder> {

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