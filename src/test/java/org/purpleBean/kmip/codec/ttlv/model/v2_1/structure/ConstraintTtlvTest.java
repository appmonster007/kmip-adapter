package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.Constraint;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Constraint Ttlv Serialization Tests")
class ConstraintTtlvTest extends AbstractTtlvSerializationTestSuite<Constraint> {

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
