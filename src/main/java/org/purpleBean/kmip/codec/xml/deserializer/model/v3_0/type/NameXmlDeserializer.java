package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.Name;

public class NameXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Name, Name.NameBuilder> {

  public NameXmlDeserializer() {
    super(Name.kmipTag, Name.encodingType);
  }

  @Override
  protected Name.NameBuilder createBuilder() {
    return Name.builder();
  }

  @Override
  protected void setValue(Name.NameBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Name build(Name.NameBuilder builder) {
    return builder.build();
  }
}