package org.purplebean.kmip.model.core.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("DrbgAlgorithm Domain Tests")
class DrbgAlgorithmTest extends AbstractKmipEnumerationTestSuite<DrbgAlgorithm> {

  @Override
  protected Class<DrbgAlgorithm> type() {
    return DrbgAlgorithm.class;
  }

  @Override
  protected DrbgAlgorithm createDefault() {
    return DrbgAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  protected DrbgAlgorithm createEqualToDefault() {
    return DrbgAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  protected DrbgAlgorithm createDifferentFromDefault() {
    return DrbgAlgorithm.Standard.DUAL_EC.inst();
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
          DrbgAlgorithm.Value byName = DrbgAlgorithm.fromName("X-Enum-Custom");
          DrbgAlgorithm.Value byVal = DrbgAlgorithm.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> DrbgAlgorithm.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in DrbgAlgorithm requires 8XXXXXXX (hex) range per implementation
    DrbgAlgorithm.Value custom =
        DrbgAlgorithm.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> DrbgAlgorithm.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> DrbgAlgorithm.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> DrbgAlgorithm.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> DrbgAlgorithm.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}

