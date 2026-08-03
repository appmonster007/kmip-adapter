package org.purplebean.kmip.codec.json.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.structure.Constraint;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Constraint Json Serialization Tests")
class ConstraintJsonTest extends AbstractJsonSerializationTestSuite<Constraint> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<Constraint> type() {
    return Constraint.class;
  }

  @Override
  public Constraint createDefault() {
    return Constraint.of(Collections.emptyList());
  }

  @Override
  public Constraint createVariant() {
    return Constraint.of(Collections.emptyList());
  }
}
