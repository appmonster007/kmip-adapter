package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Constraint;

/**
 * XML deserializer for {@link Constraint}.
 */
public class ConstraintXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Constraint, Constraint.ConstraintBuilder> {

  /**
   * Constructs a new {@link ConstraintXmlDeserializer}.
   */
  public ConstraintXmlDeserializer() {
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