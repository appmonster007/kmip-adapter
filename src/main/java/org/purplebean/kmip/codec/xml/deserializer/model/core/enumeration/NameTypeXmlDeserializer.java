package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.NameType;

public class NameTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<NameType, NameType.NameTypeBuilder> {

  public NameTypeXmlDeserializer() {
    super(NameType.kmipTag, NameType.encodingType);
  }

  @Override
  protected NameType.NameTypeBuilder createBuilder() {
    return NameType.builder();
  }

  @Override
  protected void setValue(NameType.NameTypeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(NameType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected NameType build(NameType.NameTypeBuilder builder) {
    return builder.build();
  }
}