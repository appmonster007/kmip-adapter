package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.KeyValueStructure;

/**
 * TTLV deserializer for {@link KeyValueStructure}.
 */
public class KeyValueStructureTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValueStructure,
        KeyValueStructure.KeyValueStructureBuilder> {

  /**
   * Constructs a new {@link KeyValueStructureTtlvDeserializer}.
   */
  public KeyValueStructureTtlvDeserializer() {
    super(KeyValueStructure.kmipTag, KeyValueStructure.encodingType);
  }

  @Override
  protected KeyValueStructure.KeyValueStructureBuilder createBuilder() {
    return KeyValueStructure.builder();
  }

  @Override
  protected void setValue(KeyValueStructure.KeyValueStructureBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_MATERIAL ->
          builder.keyMaterial(mapper.readValue(p, KeyMaterial.class));
      default -> builder.attribute(mapper.readValue(p, KmipAttribute.class));
    }
  }

  @Override
  protected KeyValueStructure build(KeyValueStructure.KeyValueStructureBuilder builder) {
    return builder.build();
  }
}