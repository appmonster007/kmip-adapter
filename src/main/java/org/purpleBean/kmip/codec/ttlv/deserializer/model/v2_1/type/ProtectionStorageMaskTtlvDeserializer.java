package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;

public class ProtectionStorageMaskTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtectionStorageMask,
        ProtectionStorageMask.ProtectionStorageMaskBuilder> {

  public ProtectionStorageMaskTtlvDeserializer() {
    super(ProtectionStorageMask.kmipTag, ProtectionStorageMask.encodingType);
  }

  @Override
  protected ProtectionStorageMask.ProtectionStorageMaskBuilder createBuilder() {
    return ProtectionStorageMask.builder();
  }

  @Override
  protected void setValue(ProtectionStorageMask.ProtectionStorageMaskBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ProtectionStorageMask build(
      ProtectionStorageMask.ProtectionStorageMaskBuilder builder) {
    return builder.build();
  }
}