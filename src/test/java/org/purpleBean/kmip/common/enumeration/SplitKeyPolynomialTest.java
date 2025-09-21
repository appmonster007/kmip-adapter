package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SplitKeyPolynomial Domain Tests")
class SplitKeyPolynomialTest extends AbstractKmipEnumerationSuite<SplitKeyPolynomial> {

    @Override
    protected Class<SplitKeyPolynomial> type() {
        return SplitKeyPolynomial.class;
    }

    @Override
    protected SplitKeyPolynomial createDefault() {
        return new SplitKeyPolynomial(SplitKeyPolynomial.Standard.POLYNOMIAL_283);
    }

    @Override
    protected SplitKeyPolynomial createEqualToDefault() {
        return new SplitKeyPolynomial(SplitKeyPolynomial.Standard.POLYNOMIAL_283);
    }

    @Override
    protected SplitKeyPolynomial createDifferentFromDefault() {
        return new SplitKeyPolynomial(SplitKeyPolynomial.Standard.POLYNOMIAL_285);
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
        SplitKeyPolynomial.Value byName = SplitKeyPolynomial.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        SplitKeyPolynomial.Value byVal = SplitKeyPolynomial.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> SplitKeyPolynomial.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in SplitKeyPolynomial requires 8XXXXXXX (hex) range per implementation
        SplitKeyPolynomial.Value custom = SplitKeyPolynomial.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> SplitKeyPolynomial.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SplitKeyPolynomial.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SplitKeyPolynomial.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SplitKeyPolynomial.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

