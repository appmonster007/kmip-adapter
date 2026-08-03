package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ObjectType, ObjectType.ObjectTypeBuilder> {

  public ObjectTypeXmlDeserializer() {
    super(ObjectType.kmipTag, ObjectType.encodingType);
  }

  @Override
  protected ObjectType.ObjectTypeBuilder createBuilder() {
    return ObjectType.builder();
  }

  @Override
  protected void setValue(ObjectType.ObjectTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ObjectType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ObjectType build(ObjectType.ObjectTypeBuilder builder) {
    return builder.build();
  }
}