package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("KeyCompressionType Domain Tests")
class KeyCompressionTypeTest extends AbstractKmipEnumerationSuite<KeyCompressionType> {

    @Override
    protected Class<KeyCompressionType> type() {
        return KeyCompressionType.class;
    }

    @Override
    protected KeyCompressionType createDefault() {
        return new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED);
    }

    @Override
    protected KeyCompressionType createEqualToDefault() {
        return new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED);
    }

    @Override
    protected KeyCompressionType createDifferentFromDefault() {
        return new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_PRIME);
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
                    KeyCompressionType.Value byName = KeyCompressionType.fromName("X-Enum-Custom");
                    KeyCompressionType.Value byVal = KeyCompressionType.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> KeyCompressionType.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in KeyCompressionType requires 8XXXXXXX (hex) range per implementation
        KeyCompressionType.Value custom = KeyCompressionType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> KeyCompressionType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KeyCompressionType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KeyCompressionType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KeyCompressionType.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

