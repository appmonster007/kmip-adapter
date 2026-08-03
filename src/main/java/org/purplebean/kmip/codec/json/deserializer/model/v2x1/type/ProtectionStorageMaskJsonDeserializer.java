package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ProtectionStorageMask;

/**
 * JSON deserializer for {@link ProtectionStorageMask}.
 */
public class ProtectionStorageMaskJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtectionStorageMask,
        ProtectionStorageMask.ProtectionStorageMaskBuilder> {

  /**
   * Constructs a new {@link ProtectionStorageMaskJsonDeserializer}.
   */
  public ProtectionStorageMaskJsonDeserializer() {
    super(ProtectionStorageMask.kmipTag, ProtectionStorageMask.encodingType);
  }

  @Override
  protected ProtectionStorageMask.ProtectionStorageMaskBuilder createBuilder() {
    return ProtectionStorageMask.builder();
  }

  @Override
  protected void setValue(ProtectionStorageMask.ProtectionStorageMaskBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ProtectionStorageMask build(
      ProtectionStorageMask.ProtectionStorageMaskBuilder builder) {
    return builder.build();
  }
}