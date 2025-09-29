package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("RngMode Domain Tests")
class RngModeTest extends AbstractKmipEnumerationSuite<RngMode> {

    @Override
    protected Class<RngMode> type() {
        return RngMode.class;
    }

    @Override
    protected RngMode createDefault() {
        return new RngMode(RngMode.Standard.UNSPECIFIED);
    }

    @Override
    protected RngMode createEqualToDefault() {
        return new RngMode(RngMode.Standard.UNSPECIFIED);
    }

    @Override
    protected RngMode createDifferentFromDefault() {
        return new RngMode(RngMode.Standard.SHARED_INSTANTIATION);
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
                    RngMode.Value byName = RngMode.fromName("X-Enum-Custom");
                    RngMode.Value byVal = RngMode.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> RngMode.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in RngMode requires 8XXXXXXX (hex) range per implementation
        RngMode.Value custom = RngMode.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> RngMode.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RngMode.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RngMode.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RngMode.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

