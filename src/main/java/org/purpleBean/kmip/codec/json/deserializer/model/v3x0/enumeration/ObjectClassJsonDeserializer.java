package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3x0.enumeration.ObjectClass;

public class ObjectClassJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ObjectClass, ObjectClass.ObjectClassBuilder> {

  public ObjectClassJsonDeserializer() {
    super(ObjectClass.kmipTag, ObjectClass.encodingType);
  }

  @Override
  protected ObjectClass.ObjectClassBuilder createBuilder() {
    return ObjectClass.builder();
  }

  @Override
  protected void setValue(ObjectClass.ObjectClassBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ObjectClass.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ObjectClass build(ObjectClass.ObjectClassBuilder builder) {
    return builder.build();
  }
}
