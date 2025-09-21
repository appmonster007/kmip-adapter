package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NistKeyType Domain Tests")
class NistKeyTypeTest extends AbstractKmipEnumerationSuite<NistKeyType> {

    @Override
    protected Class<NistKeyType> type() {
        return NistKeyType.class;
    }

    @Override
    protected NistKeyType createDefault() {
        return new NistKeyType(NistKeyType.Standard.PRIVATE_SIGNATURE_KEY);
    }

    @Override
    protected NistKeyType createEqualToDefault() {
        return new NistKeyType(NistKeyType.Standard.PRIVATE_SIGNATURE_KEY);
    }

    @Override
    protected NistKeyType createDifferentFromDefault() {
        return new NistKeyType(NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY);
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
        NistKeyType.Value byName = NistKeyType.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        NistKeyType.Value byVal = NistKeyType.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> NistKeyType.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in NistKeyType requires 8XXXXXXX (hex) range per implementation
        NistKeyType.Value custom = NistKeyType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> NistKeyType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NistKeyType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NistKeyType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NistKeyType.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

