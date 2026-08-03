package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.ProtectionStorageMasks;
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;

public class ProtectionStorageMasksTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtectionStorageMasks,
        ProtectionStorageMasks.ProtectionStorageMasksBuilder> {

  public ProtectionStorageMasksTtlvDeserializer() {
    super(ProtectionStorageMasks.kmipTag, ProtectionStorageMasks.encodingType);
  }

  @Override
  protected ProtectionStorageMasks.ProtectionStorageMasksBuilder createBuilder() {
    return ProtectionStorageMasks.builder();
  }

  @Override
  protected void setValue(ProtectionStorageMasks.ProtectionStorageMasksBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTECTION_STORAGE_MASK ->
          builder.protectionStorageMask(mapper.readValue(p, ProtectionStorageMask.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProtectionStorageMasks build(
      ProtectionStorageMasks.ProtectionStorageMasksBuilder builder) {
    return builder.build();
  }
}