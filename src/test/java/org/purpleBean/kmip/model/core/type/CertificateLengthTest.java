package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("CertificateLength Domain Tests")
class CertificateLengthTest extends AbstractKmipDataTypeTestSuite<CertificateLength>
    implements KmipAttributeTestSuite<CertificateLength> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<CertificateLength> type() {
    return CertificateLength.class;
  }

  @Override
  public CertificateLength createDefault() {
    return CertificateLength.of(2048);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
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
    return null; // Not modifiable by server
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.ACTIVE.inst(); // Never modifiable by server
  }

  @Override
  public State stateForClientModifiableTrue() {
    return null; // Not modifiable by client
  }

  @Override
  public State stateForClientModifiableFalse() {
    return State.Standard.ACTIVE.inst(); // Never modifiable by client
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofInteger(2048);
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    // Always false
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    // Always false
  }
}
