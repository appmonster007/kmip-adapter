package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("Link Domain Tests")
class LinkTest extends AbstractKmipStructureTestSuite<Link>
    implements KmipAttributeTestSuite<Link> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<Link> type() {
    return Link.class;
  }

  @Override
  public Link createDefault() {
    return Link
        .builder()
        .linkType(LinkType.Standard.CERTIFICATE_LINK.inst())
        .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
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
    assertThat(values.get(0)).isInstanceOf(LinkType.class);
    assertThat(values.get(1)).isInstanceOf(LinkedObjectIdentifier.class);
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
    return true;
  }

  @Override
  public boolean expectMultiInstanceAllowed() {
    return true;
  }

  @Override
  public State stateForServerModifiableTrue() {
    return State.Standard.ACTIVE.inst(); // Modifiable in any state
  }

  @Override
  public State stateForServerModifiableFalse() {
    return null; // Always modifiable
  }

  @Override
  public State stateForClientModifiableTrue() {
    return State.Standard.ACTIVE.inst(); // Modifiable in any state
  }

  @Override
  public State stateForClientModifiableFalse() {
    return null; // Always modifiable
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofStructure(createDefault().getValue());
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    // Always true
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    // Always true
  }
}
