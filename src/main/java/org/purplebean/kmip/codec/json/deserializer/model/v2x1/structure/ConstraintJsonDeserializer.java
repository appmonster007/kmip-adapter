package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Constraint;

/**
 * JSON deserializer for {@link Constraint}.
 */
public class ConstraintJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Constraint, Constraint.ConstraintBuilder> {

  /**
   * Constructs a new {@link ConstraintJsonDeserializer}.
   */
  public ConstraintJsonDeserializer() {
    super(Constraint.kmipTag, Constraint.encodingType);
  }

  @Override
  protected Constraint.ConstraintBuilder createBuilder() {
    return Constraint.builder();
  }

  @Override
  protected void setValue(Constraint.ConstraintBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, KmipDataType.class));
  }

  @Override
  protected Constraint build(Constraint.ConstraintBuilder builder) {
    return builder.build();
  }
}