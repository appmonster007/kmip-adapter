package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;

/**
 * XML deserializer for {@link KeyValueLocationValue}.
 */
public class KeyValueLocationValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<KeyValueLocationValue,
        KeyValueLocationValue.KeyValueLocationValueBuilder> {

  /**
   * Constructs a new {@link KeyValueLocationValueXmlDeserializer}.
   */
  public KeyValueLocationValueXmlDeserializer() {
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