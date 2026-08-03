package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;

public class PublicKeyAttributesXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PublicKeyAttributes,
        PublicKeyAttributes.PublicKeyAttributesBuilder> {

  public PublicKeyAttributesXmlDeserializer() {
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