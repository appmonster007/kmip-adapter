package org.purpleBean.kmip.model.v2x1.enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("UniqueIdentifier Domain Tests")
class UniqueIdentifierTest extends AbstractKmipEnumerationTestSuite<UniqueIdentifier> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<UniqueIdentifier> type() {
    return UniqueIdentifier.class;
  }

  @Override
  protected UniqueIdentifier createDefault() {
    // TODO: Replace with an actual Standard enum value, e.g., UniqueIdentifier.Standard
    //  .SOME_VALUE.inst();
    // For now, using the first available value if any exist.
    if (UniqueIdentifier.Standard.values().length > 0) {
      return UniqueIdentifier.Standard.values()[0].inst();
    }
    // Fallback for enums with no predefined Standard values (e.g., during initial generation)
    return UniqueIdentifier
        .register(0x80000001, "X-Default-Value", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  protected UniqueIdentifier createEqualToDefault() {
    // TODO: Replace with an actual Standard enum value equal to the one in createDefault()
    if (UniqueIdentifier.Standard.values().length > 0) {
      return UniqueIdentifier.Standard.values()[0].inst();
    }
    return UniqueIdentifier
        .register(0x80000001, "X-Default-Value", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  protected UniqueIdentifier createDifferentFromDefault() {
    // TODO: Replace with an actual Standard enum value different from the one in createDefault()
    if (UniqueIdentifier.Standard.values().length > 1) {
      return UniqueIdentifier.Standard.values()[1].inst();
    }
    // Fallback for enums with only one or no predefined Standard values
    return UniqueIdentifier
        .register(0x80000002, "X-Variant-Value", Set.of(KmipSpec.UnknownVersion))
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
          UniqueIdentifier.Value byName = UniqueIdentifier.fromName("X-Enum-Custom");
          UniqueIdentifier.Value byVal = UniqueIdentifier.fromValue(0x80000010);
          assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
          assertThat(byVal.getValue()).isEqualTo(0x80000010);
        }
    );

    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThatThrownBy(() -> UniqueIdentifier.fromName("X-Enum-Custom"))
    );
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // TODO: Customize these assertions based on actual enum values and expected registry behavior
    // Example:
    UniqueIdentifier.Value custom =
        UniqueIdentifier.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
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
        () -> UniqueIdentifier.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> UniqueIdentifier.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
        () -> UniqueIdentifier.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> UniqueIdentifier.register(0x80000012, "X-Empty-Versions", Set.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}