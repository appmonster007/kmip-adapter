package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.CustomAttribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * XML deserializer for {@link CustomAttribute}.
 */
public class CustomAttributeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CustomAttribute, CustomAttribute.CustomAttributeBuilder> {

  /**
   * Constructs a new {@link CustomAttributeXmlDeserializer}.
   */
  public CustomAttributeXmlDeserializer() {
    super(CustomAttribute.kmipTag, CustomAttribute.encodingType);
  }

  @Override
  protected CustomAttribute.CustomAttributeBuilder createBuilder() {
    return CustomAttribute.builder();
  }

  @Override
  protected void setValue(CustomAttribute.CustomAttributeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(ctxt.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_VALUE ->
          builder.attributeValue(ctxt.readValue(p, AttributeValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CustomAttribute build(CustomAttribute.CustomAttributeBuilder builder) {
    return builder.build();
  }
}