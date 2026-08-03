package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Objects;

/**
 * TTLV deserializer for {@link Objects}.
 */
public class ObjectsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Objects, Objects.ObjectsBuilder> {

  /**
   * Constructs a new {@link ObjectsTtlvDeserializer}.
   */
  public ObjectsTtlvDeserializer() {
    super(Objects.kmipTag, Objects.encodingType);
  }

  @Override
  protected Objects.ObjectsBuilder createBuilder() {
    return Objects.builder();
  }

  @Override
  protected void setValue(Objects.ObjectsBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(
          mapper.readValue(p, org.purplebean.kmip.model.core.type.UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Objects build(Objects.ObjectsBuilder builder) {
    return builder.build();
  }
}