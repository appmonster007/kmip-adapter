package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;

/**
 * TTLV deserializer for {@link ProtocolVersionMajor}.
 */
public class ProtocolVersionMajorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMajor,
        ProtocolVersionMajor.ProtocolVersionMajorBuilder> {

  /**
   * Constructs a new {@link ProtocolVersionMajorTtlvDeserializer}.
   */
  public ProtocolVersionMajorTtlvDeserializer() {
    super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType);
  }

  @Override
  protected ProtocolVersionMajor.ProtocolVersionMajorBuilder createBuilder() {
    return ProtocolVersionMajor.builder();
  }

  @Override
  protected void setValue(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected ProtocolVersionMajor build(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder) {
    return builder.build();
  }
}
