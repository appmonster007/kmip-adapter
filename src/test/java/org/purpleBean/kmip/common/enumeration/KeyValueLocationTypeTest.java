package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("KeyValueLocationType Domain Tests")
class KeyValueLocationTypeTest extends AbstractKmipEnumerationSuite<KeyValueLocationType> {

    @Override
    protected Class<KeyValueLocationType> type() {
        return KeyValueLocationType.class;
    }

    @Override
    protected KeyValueLocationType createDefault() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected KeyValueLocationType createEqualToDefault() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected KeyValueLocationType createDifferentFromDefault() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.URI);
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
        KeyValueLocationType.Value byName = KeyValueLocationType.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        KeyValueLocationType.Value byVal = KeyValueLocationType.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> KeyValueLocationType.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in KeyValueLocationType requires 8XXXXXXX (hex) range per implementation
        KeyValueLocationType.Value custom = KeyValueLocationType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.V1_0)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> KeyValueLocationType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KeyValueLocationType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KeyValueLocationType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KeyValueLocationType.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

