package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObjectGroupMember,
        ObjectGroupMember.ObjectGroupMemberBuilder> {

  public ObjectGroupMemberTtlvDeserializer() {
    super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType);
  }

  @Override
  protected ObjectGroupMember.ObjectGroupMemberBuilder createBuilder() {
    return ObjectGroupMember.builder();
  }

  @Override
  protected void setValue(ObjectGroupMember.ObjectGroupMemberBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ObjectGroupMember.fromValue(value));
  }

  @Override
  protected ObjectGroupMember build(ObjectGroupMember.ObjectGroupMemberBuilder builder) {
    return builder.build();
  }
}
