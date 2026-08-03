package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.model.v2x1.structure.ObjectTypes;

public class ObjectDefaultsTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObjectDefaults, ObjectDefaults.ObjectDefaultsBuilder> {

  public ObjectDefaultsTtlvDeserializer() {
    super(ObjectDefaults.kmipTag, ObjectDefaults.encodingType);
  }

  @Override
  protected ObjectDefaults.ObjectDefaultsBuilder createBuilder() {
    return ObjectDefaults.builder();
  }

  @Override
  protected void setValue(ObjectDefaults.ObjectDefaultsBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPES ->
          builder.objectTypes(mapper.readValue(p, ObjectTypes.class));
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      case KmipTag.Standard.OBJECT_GROUPS ->
          builder.objectGroups(mapper.readValue(p, ObjectGroups.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectDefaults build(ObjectDefaults.ObjectDefaultsBuilder builder) {
    return builder.build();
  }
}