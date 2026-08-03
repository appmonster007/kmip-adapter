package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.PrivateKeyAttributes;

public class PrivateKeyAttributesXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrivateKeyAttributes,
        PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

  public PrivateKeyAttributesXmlDeserializer() {
    super(PrivateKeyAttributes.kmipTag, PrivateKeyAttributes.encodingType);
  }

  @Override
  protected PrivateKeyAttributes.PrivateKeyAttributesBuilder createBuilder() {
    return PrivateKeyAttributes.builder();
  }

  @Override
  protected void setValue(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected PrivateKeyAttributes build(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder) {
    return builder.build();
  }
}