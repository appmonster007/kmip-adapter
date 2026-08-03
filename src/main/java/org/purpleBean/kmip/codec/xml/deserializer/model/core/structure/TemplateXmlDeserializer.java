package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;

public class TemplateXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Template, Template.TemplateBuilder> {

  public TemplateXmlDeserializer() {
    super(Template.kmipTag, Template.encodingType);
  }

  @Override
  protected Template.TemplateBuilder createBuilder() {
    return Template.builder();
  }

  @Override
  protected void setValue(Template.TemplateBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Template build(Template.TemplateBuilder builder) {
    return builder.build();
  }
}