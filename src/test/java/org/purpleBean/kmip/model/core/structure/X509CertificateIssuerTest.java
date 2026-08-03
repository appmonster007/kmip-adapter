package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("X509CertificateIssuer Domain Tests")
class X509CertificateIssuerTest extends AbstractKmipStructureTestSuite<X509CertificateIssuer>
    implements KmipAttributeTestSuite<X509CertificateIssuer> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<X509CertificateIssuer> type() {
    return X509CertificateIssuer.class;
  }

  @Override
  public X509CertificateIssuer createDefault() {
    return X509CertificateIssuer
        .builder()
        .issuerDistinguishedName(
            IssuerDistinguishedName.of("CN=Test Issuer".getBytes())
        )
        .issuerAlternativeName(
            IssuerAlternativeName.of("alt.issuer.com".getBytes())
        )
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
    assertThat(values.get(1)).isInstanceOf(IssuerAlternativeName.class);
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
