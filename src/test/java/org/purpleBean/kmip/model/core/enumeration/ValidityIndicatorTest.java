package org.purpleBean.kmip.model.core.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("ValidityIndicator Domain Tests")
class ValidityIndicatorTest extends AbstractKmipEnumerationTestSuite<ValidityIndicator> {

  @Override
  protected Class<ValidityIndicator> type() {
    return ValidityIndicator.class;
  }

  @Override
  protected ValidityIndicator createDefault() {
    return ValidityIndicator.Standard.VALID.inst();
  }

  @Override
  protected ValidityIndicator createEqualToDefault() {
    return ValidityIndicator.Standard.VALID.inst();
  }

  @Override
  protected ValidityIndicator createDifferentFromDefault() {
    return ValidityIndicator.Standard.INVALID.inst();
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
          ValidityIndicator.Value byName = ValidityIndicator.fromName("X-Enum-Custom");
          ValidityIndicator.Value byVal = ValidityIndicator.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> ValidityIndicator.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in ValidityIndicator requires 8XXXXXXX (hex) range per implementation
    ValidityIndicator.Value custom =
        ValidityIndicator.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> ValidityIndicator.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ValidityIndicator.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> ValidityIndicator.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> ValidityIndicator.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}

