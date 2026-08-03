package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ObjectGroup;
import org.purpleBean.kmip.model.v2x1.structure.ObjectGroups;

public class ObjectGroupsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ObjectGroups, ObjectGroups.ObjectGroupsBuilder> {

  public ObjectGroupsTtlvDeserializer() {
    super(ObjectGroups.kmipTag, ObjectGroups.encodingType);
  }

  @Override
  protected ObjectGroups.ObjectGroupsBuilder createBuilder() {
    return ObjectGroups.builder();
  }

  @Override
  protected void setValue(ObjectGroups.ObjectGroupsBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_GROUP ->
          builder.objectGroup(mapper.readValue(p, ObjectGroup.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectGroups build(ObjectGroups.ObjectGroupsBuilder builder) {
    return builder.build();
  }
}