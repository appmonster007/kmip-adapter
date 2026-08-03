package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("Name Domain Tests")
class NameTest extends AbstractKmipStructureTestSuite<Name>
    implements KmipAttributeTestSuite<Name> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<Name> type() {
    return Name.class;
  }

  @Override
  public Name createDefault() {
    return Name
        .builder()
        .nameValue(NameValue.of("test-name"))
        .nameType(NameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
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
    assertThat(values.get(0)).isInstanceOf(NameValue.class);
    assertThat(values.get(1)).isInstanceOf(NameType.class);
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
    return State.Standard.ACTIVE.inst(); // Never modifiable by server
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
    // Always false
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    // Always true
  }
}
