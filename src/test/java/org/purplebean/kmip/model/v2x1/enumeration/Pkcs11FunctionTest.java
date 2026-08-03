package org.purplebean.kmip.model.v2x1.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("Pkcs11Function Domain Tests")
class Pkcs11FunctionTest extends AbstractKmipEnumerationTestSuite<Pkcs11Function> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<Pkcs11Function> type() {
    return Pkcs11Function.class;
  }

  @Override
  protected Pkcs11Function createDefault() {
    // TODO: Replace with an actual Standard enum value, e.g., Pkcs11Function.Standard.SOME_VALUE
    //  .inst();
    // For now, using the first available value if any exist.
    if (Pkcs11Function.Standard.values().length > 0) {
      return Pkcs11Function.Standard.values()[0].inst();
    }
    // Fallback for enums with no predefined Standard values (e.g., during initial generation)
    return Pkcs11Function
        .register(0x80000001, "X-Default-Value",
            Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0))
        .inst();
  }

  @Override
  protected Pkcs11Function createEqualToDefault() {
    // TODO: Replace with an actual Standard enum value equal to the one in createDefault()
    if (Pkcs11Function.Standard.values().length > 0) {
      return Pkcs11Function.Standard.values()[0].inst();
    }
    return Pkcs11Function
        .register(0x80000001, "X-Default-Value",
            Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0))
        .inst();
  }

  @Override
  protected Pkcs11Function createDifferentFromDefault() {
    // TODO: Replace with an actual Standard enum value different from the one in createDefault()
    if (Pkcs11Function.Standard.values().length > 1) {
      return Pkcs11Function.Standard.values()[1].inst();
    }
    // Fallback for enums with only one or no predefined Standard values
    return Pkcs11Function
        .register(0x80000002, "X-Variant-Value",
            Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0))
        .inst();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }

  @Override
  protected boolean supportsRegistryBehavior() {
    // Set to true if the enum supports custom extension values and lookup methods
    return true; // Assuming all generated enums will support this
  }

  @Override
  protected void assertLookupBehaviour() {
    // TODO: Customize these assertions based on actual enum values and expected lookup behavior
    // Example:
    withKmipSpec(
        KmipSpec.UnknownVersion,
        () -> {
          Pkcs11Function.Value byName = Pkcs11Function.fromName("X-Enum-Custom");
          Pkcs11Function.Value byVal = Pkcs11Function.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> Pkcs11Function.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // TODO: Customize these assertions based on actual enum values and expected registry behavior
    // Example:
    Pkcs11Function.Value custom =
        Pkcs11Function.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> Pkcs11Function.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> Pkcs11Function.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> Pkcs11Function.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Pkcs11Function.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}