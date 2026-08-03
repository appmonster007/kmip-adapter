package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ObjectGroup, ObjectGroup.ObjectGroupBuilder> {

  public ObjectGroupJsonDeserializer() {
    super(ObjectGroup.kmipTag, ObjectGroup.encodingType);
  }

  @Override
  protected ObjectGroup.ObjectGroupBuilder createBuilder() {
    return ObjectGroup.builder();
  }

  @Override
  protected void setValue(ObjectGroup.ObjectGroupBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ObjectGroup build(ObjectGroup.ObjectGroupBuilder builder) {
    return builder.build();
  }
}
