package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ObjectGroupMember,
        ObjectGroupMember.ObjectGroupMemberBuilder> {

  public ObjectGroupMemberXmlDeserializer() {
    super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType);
  }

  @Override
  protected ObjectGroupMember.ObjectGroupMemberBuilder createBuilder() {
    return ObjectGroupMember.builder();
  }

  @Override
  protected void setValue(ObjectGroupMember.ObjectGroupMemberBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ObjectGroupMember.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ObjectGroupMember build(ObjectGroupMember.ObjectGroupMemberBuilder builder) {
    return builder.build();
  }
}