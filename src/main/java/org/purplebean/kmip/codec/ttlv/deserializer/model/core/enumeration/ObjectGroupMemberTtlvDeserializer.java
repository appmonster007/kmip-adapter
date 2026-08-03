package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectGroupMember;

/**
 * TTLV deserializer for {@link ObjectGroupMember}.
 */
public class ObjectGroupMemberTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObjectGroupMember,
        ObjectGroupMember.ObjectGroupMemberBuilder> {

  /**
   * Constructs a new {@link ObjectGroupMemberTtlvDeserializer}.
   */
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
