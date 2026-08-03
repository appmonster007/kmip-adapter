package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;

public class PrivateKeyAttributesJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrivateKeyAttributes,
        PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

  public PrivateKeyAttributesJsonDeserializer() {
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