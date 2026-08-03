package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ObjectGroup;

/**
 * TTLV deserializer for {@link ObjectGroup}.
 */
public class ObjectGroupTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ObjectGroup, ObjectGroup.ObjectGroupBuilder> {

  /**
   * Constructs a new {@link ObjectGroupTtlvDeserializer}.
   */
  public ObjectGroupTtlvDeserializer() {
    super(ObjectGroup.kmipTag, ObjectGroup.encodingType);
  }

  @Override
  protected ObjectGroup.ObjectGroupBuilder createBuilder() {
    return ObjectGroup.builder();
  }

  @Override
  protected void setValue(ObjectGroup.ObjectGroupBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ObjectGroup build(ObjectGroup.ObjectGroupBuilder builder) {
    return builder.build();
  }
}
