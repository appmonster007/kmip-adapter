package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;

public class AttributesXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Attributes, Attributes.AttributesBuilder> {

  public AttributesXmlDeserializer() {
    super(Attributes.kmipTag, Attributes.encodingType);
  }

  @Override
  protected Attributes.AttributesBuilder createBuilder() {
    return Attributes.builder();
  }

  @Override
  protected void setValue(Attributes.AttributesBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected Attributes build(Attributes.AttributesBuilder builder) {
    return builder.build();
  }
}