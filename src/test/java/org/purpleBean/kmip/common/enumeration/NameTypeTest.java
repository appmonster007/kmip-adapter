package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NameType Domain Tests")
class NameTypeTest extends AbstractKmipEnumerationSuite<NameType> {

    @Override
    protected Class<NameType> type() {
        return NameType.class;
    }

    @Override
    protected NameType createDefault() {
        return new NameType(NameType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected NameType createEqualToDefault() {
        return new NameType(NameType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected NameType createDifferentFromDefault() {
        return new NameType(NameType.Standard.URI);
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
        NameType.Value byName = NameType.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        NameType.Value byVal = NameType.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> NameType.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in NameType requires 8XXXXXXX (hex) range per implementation
        NameType.Value custom = NameType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> NameType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NameType.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

