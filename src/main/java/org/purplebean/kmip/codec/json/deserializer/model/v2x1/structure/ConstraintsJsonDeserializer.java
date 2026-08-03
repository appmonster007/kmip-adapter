package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Constraint;
import org.purplebean.kmip.model.v2x1.structure.Constraints;

public class ConstraintsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Constraints, Constraints.ConstraintsBuilder> {

  public ConstraintsJsonDeserializer() {
    super(Constraints.kmipTag, Constraints.encodingType);
  }

  @Override
  protected Constraints.ConstraintsBuilder createBuilder() {
    return Constraints.builder();
  }

  @Override
  protected void setValue(Constraints.ConstraintsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CONSTRAINT -> builder.constraint(ctxt.readValue(p, Constraint.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Constraints build(Constraints.ConstraintsBuilder builder) {
    return builder.build();
  }
}