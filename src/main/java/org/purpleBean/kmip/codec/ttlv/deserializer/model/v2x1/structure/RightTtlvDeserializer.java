package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.model.v2x1.structure.Operations;
import org.purplebean.kmip.model.v2x1.structure.Right;

public class RightTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Right, Right.RightBuilder> {

  public RightTtlvDeserializer() {
    super(Right.kmipTag, Right.encodingType);
  }

  @Override
  protected Right.RightBuilder createBuilder() {
    return Right.builder();
  }

  @Override
  protected void setValue(Right.RightBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.USAGE_LIMITS ->
          builder.usageLimits(mapper.readValue(p, UsageLimits.class));
      case KmipTag.Standard.OPERATIONS -> builder.operations(mapper.readValue(p, Operations.class));
      case KmipTag.Standard.OBJECTS -> builder.managedObjects(
          mapper.readValue(p, org.purplebean.kmip.model.v2x1.structure.Objects.class));
      case KmipTag.Standard.OBJECT_GROUPS ->
          builder.objectGroups(mapper.readValue(p, ObjectGroups.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Right build(Right.RightBuilder builder) {
    return builder.build();
  }
}