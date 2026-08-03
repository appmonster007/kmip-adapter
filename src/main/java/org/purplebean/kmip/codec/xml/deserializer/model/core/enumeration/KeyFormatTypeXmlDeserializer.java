package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<KeyFormatType, KeyFormatType.KeyFormatTypeBuilder> {

  public KeyFormatTypeXmlDeserializer() {
    super(KeyFormatType.kmipTag, KeyFormatType.encodingType);
  }

  @Override
  protected KeyFormatType.KeyFormatTypeBuilder createBuilder() {
    return KeyFormatType.builder();
  }

  @Override
  protected void setValue(KeyFormatType.KeyFormatTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(KeyFormatType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected KeyFormatType build(KeyFormatType.KeyFormatTypeBuilder builder) {
    return builder.build();
  }
}