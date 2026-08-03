package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;

/**
 * TTLV deserializer for {@link EndpointRole}.
 */
public class EndpointRoleTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<EndpointRole, EndpointRole.EndpointRoleBuilder> {

  /**
   * Constructs a new {@link EndpointRoleTtlvDeserializer}.
   */
  public EndpointRoleTtlvDeserializer() {
    super(EndpointRole.kmipTag, EndpointRole.encodingType);
  }

  @Override
  protected EndpointRole.EndpointRoleBuilder createBuilder() {
    return EndpointRole.builder();
  }

  @Override
  protected void setValue(EndpointRole.EndpointRoleBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(EndpointRole.fromValue(value));
  }

  @Override
  protected EndpointRole build(EndpointRole.EndpointRoleBuilder builder) {
    return builder.build();
  }
}
