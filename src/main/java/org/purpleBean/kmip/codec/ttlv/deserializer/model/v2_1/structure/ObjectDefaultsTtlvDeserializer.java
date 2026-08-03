package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.ObjectDefaults;
import org.purpleBean.kmip.model.v2_1.structure.ObjectGroups;
import org.purpleBean.kmip.model.v2_1.structure.ObjectTypes;

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