package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ObjectGroup;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;

public class ObjectGroupsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ObjectGroups, ObjectGroups.ObjectGroupsBuilder> {

  public ObjectGroupsJsonDeserializer() {
    super(ObjectGroups.kmipTag, ObjectGroups.encodingType);
  }

  @Override
  protected ObjectGroups.ObjectGroupsBuilder createBuilder() {
    return ObjectGroups.builder();
  }

  @Override
  protected void setValue(ObjectGroups.ObjectGroupsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_GROUP ->
          builder.objectGroup(ctxt.readValue(p, ObjectGroup.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectGroups build(ObjectGroups.ObjectGroupsBuilder builder) {
    return builder.build();
  }
}