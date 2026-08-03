package org.purpleBean.kmip.model.v3x0.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("NistSecurityCategory Domain Tests")
class NistSecurityCategoryTest extends AbstractKmipDataTypeTestSuite<NistSecurityCategory>
    implements KmipAttributeTestSuite<NistSecurityCategory> {

  private static final Integer FIXED_VALUE = 3;

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<NistSecurityCategory> type() {
    return NistSecurityCategory.class;
  }

  @Override
  public NistSecurityCategory createDefault() {
    return NistSecurityCategory.of(FIXED_VALUE);
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
    return null;
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.PRE_ACTIVE.inst();
  }

  @Override
  public State stateForClientModifiableTrue() {
    return null;
  }

  @Override
  public State stateForClientModifiableFalse() {
    return State.Standard.PRE_ACTIVE.inst();
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofInteger(FIXED_VALUE);
  }

  // NistSecurityCategory is never server-modifiable per KMIP spec §4.39
  @Override
  public void attribute_serverModifiable_respectsState() {
    NistSecurityCategory attr = createDefault();
    for (State.Standard s : State.Standard.values()) {
      assertThat(attr.isServerModifiable(s.inst()))
          .as("isServerModifiable(%s)", s)
          .isFalse();
    }
  }

  // NistSecurityCategory is never client-modifiable per KMIP spec §4.39
  @Override
  public void attribute_clientModifiable_respectsState() {
    NistSecurityCategory attr = createDefault();
    for (State.Standard s : State.Standard.values()) {
      assertThat(attr.isClientModifiable(s.inst()))
          .as("isClientModifiable(%s)", s)
          .isFalse();
    }
  }

  // AttributeName is a v1.x concept; NistSecurityCategory is V3.0-only
  @Override
  public void attribute_getAttributeName_returnsExpectedValue() {
    // getAttributeName() is not applicable for V3.0-only attributes
  }

  // Attribute round-trip via AttributeName is not applicable for V3.0-only types
  @Override
  public void attribute_roundTrip() {
    // getAttributeName() is not applicable for V3.0-only attributes
  }
}
