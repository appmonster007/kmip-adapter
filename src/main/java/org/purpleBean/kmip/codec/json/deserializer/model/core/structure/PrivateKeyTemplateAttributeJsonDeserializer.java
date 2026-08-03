package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrivateKeyTemplateAttribute,
        PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder> {

  public PrivateKeyTemplateAttributeJsonDeserializer() {
    super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType);
  }

  @Override
  protected PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder createBuilder() {
    return PrivateKeyTemplateAttribute.builder();
  }

  @Override
  protected void setValue(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder,
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
  protected PrivateKeyTemplateAttribute build(
      PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder) {
    return builder.build();
  }
}