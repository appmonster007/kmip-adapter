package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.KeyValuePresent;

public class KeyValuePresentJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyValuePresent, KeyValuePresent.KeyValuePresentBuilder> {

  public KeyValuePresentJsonDeserializer() {
    super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType);
  }

  @Override
  protected KeyValuePresent.KeyValuePresentBuilder createBuilder() {
    return KeyValuePresent.builder();
  }

  @Override
  protected void setValue(KeyValuePresent.KeyValuePresentBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected KeyValuePresent build(KeyValuePresent.KeyValuePresentBuilder builder) {
    return builder.build();
  }
}
