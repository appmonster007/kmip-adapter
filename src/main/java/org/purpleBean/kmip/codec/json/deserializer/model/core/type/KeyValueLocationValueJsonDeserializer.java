package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyValueLocationValue,
        KeyValueLocationValue.KeyValueLocationValueBuilder> {

  public KeyValueLocationValueJsonDeserializer() {
    super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType);
  }

  @Override
  protected KeyValueLocationValue.KeyValueLocationValueBuilder createBuilder() {
    return KeyValueLocationValue.builder();
  }

  @Override
  protected void setValue(KeyValueLocationValue.KeyValueLocationValueBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected KeyValueLocationValue build(
      KeyValueLocationValue.KeyValueLocationValueBuilder builder) {
    return builder.build();
  }
}
