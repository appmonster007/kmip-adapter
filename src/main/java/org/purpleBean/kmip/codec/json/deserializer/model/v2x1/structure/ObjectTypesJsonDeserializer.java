package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.ObjectTypes;

public class ObjectTypesJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ObjectTypes, ObjectTypes.ObjectTypesBuilder> {

  public ObjectTypesJsonDeserializer() {
    super(ObjectTypes.kmipTag, ObjectTypes.encodingType);
  }

  @Override
  protected ObjectTypes.ObjectTypesBuilder createBuilder() {
    return ObjectTypes.builder();
  }

  @Override
  protected void setValue(ObjectTypes.ObjectTypesBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectTypes build(ObjectTypes.ObjectTypesBuilder builder) {
    return builder.build();
  }
}