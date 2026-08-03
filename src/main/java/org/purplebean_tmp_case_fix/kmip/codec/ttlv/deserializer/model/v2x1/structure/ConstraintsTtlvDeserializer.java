package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Constraint;
import org.purplebean.kmip.model.v2x1.structure.Constraints;

public class ConstraintsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Constraints, Constraints.ConstraintsBuilder> {

  public ConstraintsTtlvDeserializer() {
    super(Constraints.kmipTag, Constraints.encodingType);
  }

  @Override
  protected Constraints.ConstraintsBuilder createBuilder() {
    return Constraints.builder();
  }

  @Override
  protected void setValue(Constraints.ConstraintsBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CONSTRAINT -> builder.constraint(mapper.readValue(p, Constraint.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Constraints build(Constraints.ConstraintsBuilder builder) {
    return builder.build();
  }
}