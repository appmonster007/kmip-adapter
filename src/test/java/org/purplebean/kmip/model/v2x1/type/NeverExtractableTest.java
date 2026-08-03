package org.purplebean.kmip.model.v2x1.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purplebean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("NeverExtractable Domain Tests")
class NeverExtractableTest extends AbstractKmipDataTypeTestSuite<NeverExtractable>
    implements KmipAttributeTestSuite<NeverExtractable> {

  // TODO: Adjust FIXED_VALUE based on DATA_TYPE
  private static final Boolean FIXED_VALUE = true;

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1; // TODO: Adjust default spec if needed
  }

  @Override
  protected Class<NeverExtractable> type() {
    return NeverExtractable.class;
  }

  @Override
  public NeverExtractable createDefault() {
    return NeverExtractable.of(FIXED_VALUE);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }

  @Override
  public boolean expectAlwaysPresent() {
    return true;
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
    return AttributeValue.ofBoolean(FIXED_VALUE);
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    assertThat(createDefault().isServerModifiable(stateForServerModifiableTrue())).isTrue();
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
