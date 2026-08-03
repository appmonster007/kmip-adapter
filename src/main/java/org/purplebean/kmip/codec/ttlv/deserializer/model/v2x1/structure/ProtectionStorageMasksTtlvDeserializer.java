package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.ProtectionStorageMasks;
import org.purplebean.kmip.model.v2x1.type.ProtectionStorageMask;

/**
 * TTLV deserializer for {@link ProtectionStorageMasks}.
 */
public class ProtectionStorageMasksTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtectionStorageMasks,
        ProtectionStorageMasks.ProtectionStorageMasksBuilder> {

  /**
   * Constructs a new {@link ProtectionStorageMasksTtlvDeserializer}.
   */
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