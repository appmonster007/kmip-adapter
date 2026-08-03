package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

public class AttributesJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Attributes, Attributes.AttributesBuilder> {

  public AttributesJsonDeserializer() {
    super(Attributes.kmipTag, Attributes.encodingType);
  }

  @Override
  protected Attributes.AttributesBuilder createBuilder() {
    return Attributes.builder();
  }

  @Override
  protected void setValue(Attributes.AttributesBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected Attributes build(Attributes.AttributesBuilder builder) {
    return builder.build();
  }
}