package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Fips186Variation Domain Tests")
class Fips186VariationTest extends AbstractKmipEnumerationSuite<Fips186Variation> {

    @Override
    protected Class<Fips186Variation> type() {
        return Fips186Variation.class;
    }

    @Override
    protected Fips186Variation createDefault() {
        return new Fips186Variation(Fips186Variation.Standard.UNSPECIFIED);
    }

    @Override
    protected Fips186Variation createEqualToDefault() {
        return new Fips186Variation(Fips186Variation.Standard.UNSPECIFIED);
    }

    @Override
    protected Fips186Variation createDifferentFromDefault() {
        return new Fips186Variation(Fips186Variation.Standard.GP_X_ORIGINAL);
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
        Fips186Variation.Value byName = Fips186Variation.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        Fips186Variation.Value byVal = Fips186Variation.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> Fips186Variation.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in Fips186Variation requires 8XXXXXXX (hex) range per implementation
        Fips186Variation.Value custom = Fips186Variation.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> Fips186Variation.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Fips186Variation.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Fips186Variation.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Fips186Variation.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

