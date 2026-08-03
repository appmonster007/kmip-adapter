package org.purplebean.kmip.model.core.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("ShreddingAlgorithm Domain Tests")
class ShreddingAlgorithmTest extends AbstractKmipEnumerationTestSuite<ShreddingAlgorithm> {

  @Override
  protected Class<ShreddingAlgorithm> type() {
    return ShreddingAlgorithm.class;
  }

  @Override
  protected ShreddingAlgorithm createDefault() {
    return ShreddingAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  protected ShreddingAlgorithm createEqualToDefault() {
    return ShreddingAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  protected ShreddingAlgorithm createDifferentFromDefault() {
    return ShreddingAlgorithm.Standard.CRYPTOGRAPHIC.inst();
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
          ShreddingAlgorithm.Value byName = ShreddingAlgorithm.fromName("X-Enum-Custom");
          ShreddingAlgorithm.Value byVal = ShreddingAlgorithm.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> ShreddingAlgorithm.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in ShreddingAlgorithm requires 8XXXXXXX (hex) range per implementation
    ShreddingAlgorithm.Value custom =
        ShreddingAlgorithm.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> ShreddingAlgorithm.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ShreddingAlgorithm.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ShreddingAlgorithm.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> ShreddingAlgorithm.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}

