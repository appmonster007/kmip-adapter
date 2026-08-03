package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.Description;

public class DescriptionXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Description, Description.DescriptionBuilder> {

  public DescriptionXmlDeserializer() {
    super(Description.kmipTag, Description.encodingType);
  }

  @Override
  protected Description.DescriptionBuilder createBuilder() {
    return Description.builder();
  }

  @Override
  protected void setValue(Description.DescriptionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Description build(Description.DescriptionBuilder builder) {
    return builder.build();
  }
}