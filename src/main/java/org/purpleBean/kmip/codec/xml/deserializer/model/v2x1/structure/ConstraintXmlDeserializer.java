package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.Constraint;

public class ConstraintXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Constraint, Constraint.ConstraintBuilder> {

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