package org.purpleBean.kmip.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("AdjustmentType Domain Tests")
class AdjustmentTypeTest extends AbstractKmipEnumerationTestSuite<AdjustmentType> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<AdjustmentType> type() {
        return AdjustmentType.class;
    }

    @Override
    protected AdjustmentType createDefault() {
        // For now, using the first available value if any exist.
        if (AdjustmentType.Standard.values().length > 0) {
            return AdjustmentType.Standard.values()[0].inst();
        }
        // Fallback for enums with no predefined Standard values (e.g., during initial generation)
        // This will likely fail if the enum has no values, but that's expected for an incomplete enum.
        return AdjustmentType.register(0x80000001, "X-Default-Value-1", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    protected AdjustmentType createEqualToDefault() {
        if (AdjustmentType.Standard.values().length > 0) {
            return AdjustmentType.Standard.values()[0].inst();
        }
        return new AdjustmentType(AdjustmentType.register(0x80000001, "X-Default-Value-1", Set.of(KmipSpec.UnknownVersion)));
    }

    @Override
    protected AdjustmentType createDifferentFromDefault() {
        if (AdjustmentType.Standard.values().length > 1) {
            return AdjustmentType.Standard.values()[1].inst();
        }
        // Fallback for enums with only one or no predefined Standard values
        return AdjustmentType.register(0x80000002, "X-Variant-Value-2", Set.of(KmipSpec.UnknownVersion)).inst();
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
                    AdjustmentType.Value byName = AdjustmentType.fromName("X-Enum-Custom");
                    AdjustmentType.Value byVal = AdjustmentType.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> AdjustmentType.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in AdjustmentType requires 8XXXXXXX (hex) range per implementation
        AdjustmentType.Value custom = AdjustmentType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> AdjustmentType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AdjustmentType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AdjustmentType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AdjustmentType.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

