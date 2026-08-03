package org.purpleBean.kmip.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("DeactivationDate Domain Tests")
class DeactivationDateTest extends AbstractKmipDataTypeTestSuite<DeactivationDate>
    implements KmipAttributeTestSuite<DeactivationDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<DeactivationDate> type() {
    return DeactivationDate.class;
  }

  @Override
  public DeactivationDate createDefault() {
    return DeactivationDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.DATE_TIME;
  }

  @Override
  public boolean expectAlwaysPresent() {
    return false;
  }

  @Override
  public boolean expectServerInitializable() {
    return true;
  }

  @Override
  public boolean expectClientInitializable() {
    return true;
  }

  @Override
  public boolean expectClientDeletable() {
    return false;
  }

  @Override
  public boolean expectMultiInstanceAllowed() {
    return false;
  }

  @Override
  public State stateForServerModifiableTrue() {
    return State.Standard.PRE_ACTIVE.inst();
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.DEACTIVATED.inst();
  }

  @Override
  public State stateForClientModifiableTrue() {
    return State.Standard.PRE_ACTIVE.inst();
  }

  @Override
  public State stateForClientModifiableFalse() {
    return State.Standard.DEACTIVATED.inst();
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofDateTime(FIXED_TIME);
  }
}
