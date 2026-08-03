package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("X509CertificateIdentifier Domain Tests")
class X509CertificateIdentifierTest
    extends AbstractKmipStructureTestSuite<X509CertificateIdentifier>
    implements KmipAttributeTestSuite<X509CertificateIdentifier> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<X509CertificateIdentifier> type() {
    return X509CertificateIdentifier.class;
  }

  @Override
  public X509CertificateIdentifier createDefault() {
    return X509CertificateIdentifier
        .builder()
        .issuerDistinguishedName(IssuerDistinguishedName.of(ByteBuffer.wrap(new byte[] {1, 2, 3})))
        .certificateSerialNumber(CertificateSerialNumber.of(ByteBuffer.wrap(new byte[] {4, 5, 6})))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values.get(0)).isInstanceOf(IssuerDistinguishedName.class);
    assertThat(values.get(1)).isInstanceOf(CertificateSerialNumber.class);
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
    return AttributeValue.ofStructure(createDefault().getValue());
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
