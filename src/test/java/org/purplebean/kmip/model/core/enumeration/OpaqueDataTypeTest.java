package org.purplebean.kmip.model.core.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("OpaqueDataType Domain Tests")
class OpaqueDataTypeTest extends AbstractKmipEnumerationTestSuite<OpaqueDataType> {

  @Override
  protected Class<OpaqueDataType> type() {
    return OpaqueDataType.class;
  }

  @Override
  protected OpaqueDataType createDefault() {
    return OpaqueDataType
        .register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  protected OpaqueDataType createEqualToDefault() {
    return OpaqueDataType
        .register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  protected OpaqueDataType createDifferentFromDefault() {
    return OpaqueDataType
        .register(0x80000001, "Custom2", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }

  @Override
  protected boolean supportsRegistryBehavior() {
    return true;
  }

  @Override
  protected void assertLookupBehaviour() {
    // Lookup by name/value
    withKmipSpec(
        KmipSpec.UnknownVersion,
        () -> {
          OpaqueDataType.Value byName = OpaqueDataType.fromName("X-Enum-Custom");
          OpaqueDataType.Value byVal = OpaqueDataType.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> OpaqueDataType.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in OpaqueDataType requires 8XXXXXXX (hex) range per implementation
    OpaqueDataType.Value custom =
        OpaqueDataType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> OpaqueDataType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> OpaqueDataType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> OpaqueDataType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> OpaqueDataType.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}

