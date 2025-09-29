package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("EndpointRole Domain Tests")
class EndpointRoleTest extends AbstractKmipEnumerationSuite<EndpointRole> {

    @Override
    protected Class<EndpointRole> type() {
        return EndpointRole.class;
    }

    @Override
    protected EndpointRole createDefault() {
        return new EndpointRole(EndpointRole.Standard.CLIENT);
    }

    @Override
    protected EndpointRole createEqualToDefault() {
        return new EndpointRole(EndpointRole.Standard.CLIENT);
    }

    @Override
    protected EndpointRole createDifferentFromDefault() {
        return new EndpointRole(EndpointRole.Standard.SERVER);
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
                    EndpointRole.Value byName = EndpointRole.fromName("X-Enum-Custom");
                    EndpointRole.Value byVal = EndpointRole.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> EndpointRole.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in EndpointRole requires 8XXXXXXX (hex) range per implementation
        EndpointRole.Value custom = EndpointRole.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> EndpointRole.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> EndpointRole.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> EndpointRole.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> EndpointRole.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

