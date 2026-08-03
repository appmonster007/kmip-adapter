package org.purpleBean.kmip.model.v2_1.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("RotateGeneration Domain Tests")
class RotateGenerationTest extends AbstractKmipDataTypeTestSuite<RotateGeneration>
    implements KmipAttributeTestSuite<RotateGeneration> {

  // TODO: Adjust FIXED_VALUE based on DATA_TYPE
  private static final Integer FIXED_VALUE = 123;

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1; // TODO: Adjust default spec if needed
  }

  @Override
  protected Class<RotateGeneration> type() {
    return RotateGeneration.class;
  }

  @Override
  public RotateGeneration createDefault() {
    return RotateGeneration.of(FIXED_VALUE);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
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
    return false;
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
    return State.Standard.PRE_ACTIVE.inst(); // TODO: Adjust as needed
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.ACTIVE.inst(); // TODO: Adjust as needed
  }

  @Override
  public State stateForClientModifiableTrue() {
    return State.Standard.PRE_ACTIVE.inst(); // TODO: Adjust as needed
  }

  @Override
  public State stateForClientModifiableFalse() {
    return State.Standard.ACTIVE.inst(); // TODO: Adjust as needed
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofInteger(FIXED_VALUE);
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    assertThat(createDefault().isServerModifiable(stateForServerModifiableFalse())).isFalse();
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    assertThat(createDefault().isClientModifiable(stateForClientModifiableFalse())).isFalse();
  }

  @Override
  public void attribute_roundTrip() {
    // Not applicable: Attribute wrapper structure only supports V1.x; these types are V2.1+ only
  }
}
