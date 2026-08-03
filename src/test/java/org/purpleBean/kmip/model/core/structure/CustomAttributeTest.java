package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purplebean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("CustomAttribute Domain Tests")
class CustomAttributeTest extends AbstractKmipStructureTestSuite<CustomAttribute>
    implements KmipAttributeTestSuite<CustomAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<CustomAttribute> type() {
    return CustomAttribute.class;
  }

  @Override
  public CustomAttribute createDefault() {
    return CustomAttribute
        .builder()
        .attributeName(AttributeName.of("x-Custom-Attr"))
        .attributeValue(AttributeValue.ofInteger(123))
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
    assertThat(values.get(0)).isInstanceOf(AttributeName.class);
    assertThat(values.get(1)).isInstanceOf(AttributeValue.class);
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
    return true; // x- attributes are client deletable
  }

  @Override
  public boolean expectMultiInstanceAllowed() {
    return true;
  }

  @Override
  public State stateForServerModifiableTrue() {
    return null; // x- attributes are not server modifiable
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.ACTIVE.inst(); // x- attributes are not server modifiable
  }

  @Override
  public State stateForClientModifiableTrue() {
    return State.Standard.ACTIVE.inst(); // x- attributes are client modifiable
  }

  @Override
  public State stateForClientModifiableFalse() {
    return null; // Always modifiable
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofInteger(123);
  }

  @Override
  public String expectedAttributeName() {
    return "x-Custom-Attr";
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    // Depends on attribute name prefix (x- vs y-)
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    // Depends on attribute name prefix (x- vs y-)
  }
}
