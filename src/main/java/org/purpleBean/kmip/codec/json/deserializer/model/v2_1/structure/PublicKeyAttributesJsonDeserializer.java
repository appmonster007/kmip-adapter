package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;

public class PublicKeyAttributesJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PublicKeyAttributes,
        PublicKeyAttributes.PublicKeyAttributesBuilder> {

  public PublicKeyAttributesJsonDeserializer() {
    super(PublicKeyAttributes.kmipTag, PublicKeyAttributes.encodingType);
  }

  @Override
  protected PublicKeyAttributes.PublicKeyAttributesBuilder createBuilder() {
    return PublicKeyAttributes.builder();
  }

  @Override
  protected void setValue(PublicKeyAttributes.PublicKeyAttributesBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected PublicKeyAttributes build(PublicKeyAttributes.PublicKeyAttributesBuilder builder) {
    return builder.build();
  }
}