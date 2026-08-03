package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PublicKeyTemplateAttribute;

public class PublicKeyTemplateAttributeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PublicKeyTemplateAttribute,
        PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder> {

  public PublicKeyTemplateAttributeJsonDeserializer() {
    super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType);
  }

  @Override
  protected PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder createBuilder() {
    return PublicKeyTemplateAttribute.builder();
  }

  @Override
  protected void setValue(PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PublicKeyTemplateAttribute build(
      PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder) {
    return builder.build();
  }
}
