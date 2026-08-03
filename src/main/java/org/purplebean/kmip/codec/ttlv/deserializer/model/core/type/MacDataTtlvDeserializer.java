package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MacData;

/**
 * TTLV deserializer for {@link MacData}.
 */
public class MacDataTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<MacData, MacData.MacDataBuilder> {

  /**
   * Constructs a new {@link MacDataTtlvDeserializer}.
   */
  public MacDataTtlvDeserializer() {
    super(MacData.kmipTag, MacData.encodingType);
  }

  @Override
  protected MacData.MacDataBuilder createBuilder() {
    return MacData.builder();
  }

  @Override
  protected void setValue(MacData.MacDataBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected MacData build(MacData.MacDataBuilder builder) {
    return builder.build();
  }
}
