package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;

/**
 * XML deserializer for {@link TemplateAttribute}.
 */
public class TemplateAttributeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<TemplateAttribute,
        TemplateAttribute.TemplateAttributeBuilder> {

  /**
   * Constructs a new {@link TemplateAttributeXmlDeserializer}.
   */
  public TemplateAttributeXmlDeserializer() {
    super(TemplateAttribute.kmipTag, TemplateAttribute.encodingType);
  }

  @Override
  protected TemplateAttribute.TemplateAttributeBuilder createBuilder() {
    return TemplateAttribute.builder();
  }

  @Override
  protected void setValue(TemplateAttribute.TemplateAttributeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NAME -> builder.name(ctxt.readValue(p, Name.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TemplateAttribute build(TemplateAttribute.TemplateAttributeBuilder builder) {
    return builder.build();
  }
}