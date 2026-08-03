package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("CryptographicLength Domain Tests")
class CryptographicLengthTest extends AbstractKmipDataTypeTestSuite<CryptographicLength>
    implements KmipAttributeTestSuite<CryptographicLength> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<CryptographicLength> type() {
    return CryptographicLength.class;
  }

  @Override
  public CryptographicLength createDefault() {
    return CryptographicLength.of(256);
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
    return false;
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
    return AttributeValue.ofInteger(256);
  }
}
