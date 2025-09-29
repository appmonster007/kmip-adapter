package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DestroyAction Domain Tests")
class DestroyActionTest extends AbstractKmipEnumerationSuite<DestroyAction> {

    @Override
    protected Class<DestroyAction> type() {
        return DestroyAction.class;
    }

    @Override
    protected DestroyAction createDefault() {
        return new DestroyAction(DestroyAction.Standard.UNSPECIFIED);
    }

    @Override
    protected DestroyAction createEqualToDefault() {
        return new DestroyAction(DestroyAction.Standard.UNSPECIFIED);
    }

    @Override
    protected DestroyAction createDifferentFromDefault() {
        return new DestroyAction(DestroyAction.Standard.KEY_MATERIAL_DELETED);
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
                    DestroyAction.Value byName = DestroyAction.fromName("X-Enum-Custom");
                    DestroyAction.Value byVal = DestroyAction.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> DestroyAction.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in DestroyAction requires 8XXXXXXX (hex) range per implementation
        DestroyAction.Value custom = DestroyAction.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> DestroyAction.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DestroyAction.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DestroyAction.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DestroyAction.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

