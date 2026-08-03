package org.purpleBean.kmip.model.core.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("UsageLimitsUnit Domain Tests")
class UsageLimitsUnitTest extends AbstractKmipEnumerationTestSuite<UsageLimitsUnit> {

  @Override
  protected Class<UsageLimitsUnit> type() {
    return UsageLimitsUnit.class;
  }

  @Override
  protected UsageLimitsUnit createDefault() {
    return UsageLimitsUnit.Standard.BYTE.inst();
  }

  @Override
  protected UsageLimitsUnit createEqualToDefault() {
    return UsageLimitsUnit.Standard.BYTE.inst();
  }

  @Override
  protected UsageLimitsUnit createDifferentFromDefault() {
    return UsageLimitsUnit.Standard.OBJECT.inst();
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
          UsageLimitsUnit.Value byName = UsageLimitsUnit.fromName("X-Enum-Custom");
          UsageLimitsUnit.Value byVal = UsageLimitsUnit.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    // Lookup by name/value with unsupported version
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> UsageLimitsUnit.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // Valid registration in UsageLimitsUnit requires 8XXXXXXX (hex) range per implementation
    UsageLimitsUnit.Value custom =
        UsageLimitsUnit.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> UsageLimitsUnit.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> UsageLimitsUnit.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> UsageLimitsUnit.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> UsageLimitsUnit.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}

