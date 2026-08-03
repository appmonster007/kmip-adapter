package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ObjectGroup, ObjectGroup.ObjectGroupBuilder> {

  public ObjectGroupXmlDeserializer() {
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