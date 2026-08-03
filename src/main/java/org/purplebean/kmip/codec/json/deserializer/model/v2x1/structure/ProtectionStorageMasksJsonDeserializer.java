package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.ProtectionStorageMasks;
import org.purplebean.kmip.model.v2x1.type.ProtectionStorageMask;

/**
 * JSON deserializer for {@link ProtectionStorageMasks}.
 */
public class ProtectionStorageMasksJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtectionStorageMasks,
        ProtectionStorageMasks.ProtectionStorageMasksBuilder> {

  /**
   * Constructs a new {@link ProtectionStorageMasksJsonDeserializer}.
   */
  public ProtectionStorageMasksJsonDeserializer() {
    super(ProtectionStorageMasks.kmipTag, ProtectionStorageMasks.encodingType);
  }

  @Override
  protected ProtectionStorageMasks.ProtectionStorageMasksBuilder createBuilder() {
    return ProtectionStorageMasks.builder();
  }

  @Override
  protected void setValue(ProtectionStorageMasks.ProtectionStorageMasksBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTECTION_STORAGE_MASK ->
          builder.protectionStorageMask(ctxt.readValue(p, ProtectionStorageMask.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProtectionStorageMasks build(
      ProtectionStorageMasks.ProtectionStorageMasksBuilder builder) {
    return builder.build();
  }
}