package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;

/**
 * TTLV deserializer for {@link CryptographicUsageMask}.
 */
public class CryptographicUsageMaskTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CryptographicUsageMask,
        CryptographicUsageMask.CryptographicUsageMaskBuilder> {

  /**
   * Constructs a new {@link CryptographicUsageMaskTtlvDeserializer}.
   */
  public CryptographicUsageMaskTtlvDeserializer() {
    super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType);
  }

  @Override
  protected CryptographicUsageMask.CryptographicUsageMaskBuilder createBuilder() {
    return CryptographicUsageMask.builder();
  }

  @Override
  protected void setValue(CryptographicUsageMask.CryptographicUsageMaskBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected CryptographicUsageMask build(
      CryptographicUsageMask.CryptographicUsageMaskBuilder builder) {
    return builder.build();
  }
}
