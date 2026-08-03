package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrivateKeyTemplateAttribute,
        PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder> {

  public PrivateKeyTemplateAttributeXmlDeserializer() {
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
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PrivateKeyTemplateAttribute build(
      PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder) {
    return builder.build();
  }
}