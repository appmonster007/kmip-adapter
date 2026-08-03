package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.structure.Name;

/**
 * XML deserializer for {@link CommonTemplateAttribute}.
 */
public class CommonTemplateAttributeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CommonTemplateAttribute,
        CommonTemplateAttribute.CommonTemplateAttributeBuilder> {

  /**
   * Constructs a new {@link CommonTemplateAttributeXmlDeserializer}.
   */
  public CommonTemplateAttributeXmlDeserializer() {
    super(CommonTemplateAttribute.kmipTag, CommonTemplateAttribute.encodingType);
  }

  @Override
  protected CommonTemplateAttribute.CommonTemplateAttributeBuilder createBuilder() {
    return CommonTemplateAttribute.builder();
  }

  @Override
  protected void setValue(CommonTemplateAttribute.CommonTemplateAttributeBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NAME -> builder.name(ctxt.readValue(p, Name.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CommonTemplateAttribute build(
      CommonTemplateAttribute.CommonTemplateAttributeBuilder builder) {
    return builder.build();
  }
}