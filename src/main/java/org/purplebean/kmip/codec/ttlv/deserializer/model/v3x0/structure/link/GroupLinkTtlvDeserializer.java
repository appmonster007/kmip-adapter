package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.structure.link.GroupLink;

/**
 * TTLV deserializer for {@link GroupLink}.
 */
public class GroupLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GroupLink, GroupLink.GroupLinkBuilder> {

  /**
   * Constructs a new {@link GroupLinkTtlvDeserializer}.
   */
  public GroupLinkTtlvDeserializer() {
    super(GroupLink.kmipTag, GroupLink.encodingType);
  }

  @Override
  protected GroupLink.GroupLinkBuilder createBuilder() {
    return GroupLink.builder();
  }

  @Override
  protected void setValue(GroupLink.GroupLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected GroupLink build(GroupLink.GroupLinkBuilder builder) {
    return builder.build();
  }
}
