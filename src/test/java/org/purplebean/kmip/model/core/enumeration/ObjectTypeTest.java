package org.purplebean.kmip.model.core.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractKmipEnumerationTestSuite;
import org.purplebean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("ObjectType Domain Tests")
class ObjectTypeTest extends AbstractKmipEnumerationTestSuite<ObjectType>
    implements KmipAttributeTestSuite<ObjectType> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ObjectType> type() {
    return ObjectType.class;
  }

  @Override
  public ObjectType createDefault() {
    return ObjectType.Standard.CERTIFICATE.inst();
  }

  @Override
  protected ObjectType createEqualToDefault() {
    return ObjectType.Standard.CERTIFICATE.inst();
  }

  @Override
  protected ObjectType createDifferentFromDefault() {
    return ObjectType.Standard.SYMMETRIC_KEY.inst();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }

  @Override
  protected boolean supportsRegistryBehavior() {
    return true;
  }

  // Implementation of KmipAttributeTestSuite methods
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
    return null; // Not modifiable by server in any state
  }

  @Override
  public State stateForServerModifiableFalse() {
    return State.Standard.PRE_ACTIVE.inst(); // Any state would work since it's not modifiable
  }

  @Override
  public State stateForClientModifiableTrue() {
    return null; // Not modifiable by client in any state
  }

  @Override
  public State stateForClientModifiableFalse() {
    return State.Standard.ACTIVE.inst(); // Any state would work since it's not modifiable
  }

  @Override
  public AttributeValue expectedAttributeValue() {
    return AttributeValue.ofEnumeration(ObjectType.Standard.CERTIFICATE);
  }

  @Override
  public void attribute_serverModifiable_respectsState() {
    // Not applicable as it's not server modifiable
  }

  @Override
  public void attribute_clientModifiable_respectsState() {
    // Not applicable as it's not client modifiable
  }

  @Override
  protected void assertLookupBehaviour() {
    // Lookup by name/value
    withKmipSpec(
        KmipSpec.UnknownVersion,
        () -> {
          ObjectType.Value byName = ObjectType.fromName("X-Enum-Custom");
          ObjectType.Value byVal = ObjectType.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> ObjectType.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in ObjectType requires 8XXXXXXX (hex) range per implementation
    ObjectType.Value custom =
        ObjectType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
    assertThat(custom.isCustom()).isTrue();
    assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

    withKmipSpec(KmipSpec.UnknownVersion, () -> {
      assertThat(custom.isSupported()).isTrue();
    });
    withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
      assertThat(custom.isSupported()).isFalse();
    });

    // Negative cases: invalid range, empty description, empty versions
    assertThatThrownBy(
        () -> ObjectType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ObjectType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ObjectType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> ObjectType.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
