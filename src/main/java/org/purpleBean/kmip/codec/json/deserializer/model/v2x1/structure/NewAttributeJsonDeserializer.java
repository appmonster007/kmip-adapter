package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;

public class NewAttributeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<NewAttribute, NewAttribute.NewAttributeBuilder> {

  public NewAttributeJsonDeserializer() {
    super(NewAttribute.kmipTag, NewAttribute.encodingType);
  }

  @Override
  protected NewAttribute.NewAttributeBuilder createBuilder() {
    return NewAttribute.builder();
  }

  @Override
  protected void setValue(NewAttribute.NewAttributeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected NewAttribute build(NewAttribute.NewAttributeBuilder builder) {
    return builder.build();
  }
}
