package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purplebean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("KeyValueLocation Domain Tests")
class KeyValueLocationTest extends AbstractKmipStructureTestSuite<KeyValueLocation>
    implements KmipAttributeTestSuite<KeyValueLocation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<KeyValueLocation> type() {
    return KeyValueLocation.class;
  }

  @Override
  public KeyValueLocation createDefault() {
    return KeyValueLocation
        .builder()
        .keyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .keyValueLocationValue(KeyValueLocationValue
            .builder()
            .value("test")
            .build())
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
    assertThat(values.get(0)).isInstanceOf(KeyValueLocationValue.class);
    assertThat(values.get(1)).isInstanceOf(KeyValueLocationType.class);
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
