package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<KeyPartIdentifier,
        KeyPartIdentifier.KeyPartIdentifierBuilder> {

  public KeyPartIdentifierXmlDeserializer() {
    super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType);
  }

  @Override
  protected KeyPartIdentifier.KeyPartIdentifierBuilder createBuilder() {
    return KeyPartIdentifier.builder();
  }

  @Override
  protected void setValue(KeyPartIdentifier.KeyPartIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected KeyPartIdentifier build(KeyPartIdentifier.KeyPartIdentifierBuilder builder) {
    return builder.build();
  }
}