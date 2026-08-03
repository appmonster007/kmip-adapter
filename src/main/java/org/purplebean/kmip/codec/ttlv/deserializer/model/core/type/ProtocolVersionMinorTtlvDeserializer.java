package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;

/**
 * TTLV deserializer for {@link ProtocolVersionMinor}.
 */
public class ProtocolVersionMinorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMinor,
        ProtocolVersionMinor.ProtocolVersionMinorBuilder> {

  /**
   * Constructs a new {@link ProtocolVersionMinorTtlvDeserializer}.
   */
  public ProtocolVersionMinorTtlvDeserializer() {
    super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType);
  }

  @Override
  protected ProtocolVersionMinor.ProtocolVersionMinorBuilder createBuilder() {
    return ProtocolVersionMinor.builder();
  }

  @Override
  protected void setValue(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected ProtocolVersionMinor build(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder) {
    return builder.build();
  }
}
