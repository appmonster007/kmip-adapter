package org.purpleBean.kmip.model.v3_0.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("Name Domain Tests")
class NameTest extends AbstractKmipDataTypeTestSuite<Name> implements KmipAttributeTestSuite<Name> {

  // TODO: Adjust FIXED_VALUE based on DATA_TYPE
  private static final String FIXED_VALUE = "default-string";

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<Name> type() {
    return Name.class;
  }

  @Override
  public Name createDefault() {
    return Name.of(FIXED_VALUE);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }

  @Override
  public boolean expectAlwaysPresent() {
    return false;
  }

  @Override
  public boolean expectServerInitializable() {
    return false;
  }

  @Override
  public boolean expectClientInitializable() {
    return true;
  }

  @Override
  public boolean expectClientDeletable() {
    return true;
  }

  @Override
  public boolean expectMultiInstanceAllowed() {
    return true;
  }

  @Override
  public State stateForServerModifiableTrue() {
    return null; // Not modifiable by server
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.ACTIVE.inst();
  }

  @Override
  public State stateForClientModifiableTrue() {
    return State.Standard.PRE_ACTIVE.inst();
  }

  @Override
  public State stateForClientModifiableFalse() {
    return State.Standard.ACTIVE.inst();
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofTextString(FIXED_VALUE);
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    assertThat(createDefault().isServerModifiable(stateForServerModifiableFalse())).isFalse();
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    assertThat(createDefault().isClientModifiable(stateForClientModifiableTrue())).isTrue();
  }

  @Override
  public void attribute_roundTrip() {
    // Not applicable: Attribute wrapper structure only supports V1.x; this type is V3.0+ only
  }
}