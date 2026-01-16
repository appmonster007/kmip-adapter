package org.purpleBean.kmip.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationAttributeTestSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DigitalSignatureAlgorithm Domain Tests")
class DigitalSignatureAlgorithmTest extends AbstractKmipEnumerationAttributeTestSuite<DigitalSignatureAlgorithm> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2; // TODO: Adjust if needed
    }

    @Override
    protected Class<DigitalSignatureAlgorithm> type() {
        return DigitalSignatureAlgorithm.class;
    }

    @Override
    protected DigitalSignatureAlgorithm createDefault() {
        return DigitalSignatureAlgorithm.Standard.values()[0].inst();
    }

    @Override
    protected DigitalSignatureAlgorithm createEqualToDefault() {
        return DigitalSignatureAlgorithm.Standard.values()[0].inst();
    }

    @Override
    protected DigitalSignatureAlgorithm createDifferentFromDefault() {
        return DigitalSignatureAlgorithm.Standard.values()[1].inst();
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
    public boolean expectAlwaysPresent() {
        return true;
    }

    @Override
    public boolean expectServerInitializable() {
        return true;
    }

    @Override
    public boolean expectClientInitializable() {
        return false;
    }

    @Override
    public boolean expectClientDeletable() {
        return false;
    }

    @Override
    public boolean expectMultiInstanceAllowed() {
        return true;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return null;
    }

    @Override
    public State stateForServerModifiableFalse() {
        return null;
    }

    @Override
    public State stateForClientModifiableTrue() {
        return null;
    }

    @Override
    public State stateForClientModifiableFalse() {
        return null;
    }

    @Override
    protected void attrEnum_serverModifiable_respectsState() {
        // TODO: Implement if server modifiable
    }

    @Override
    protected void attrEnum_clientModifiable_respectsState() {
        // TODO: Implement if client modifiable
    }

    @Override
    protected void assertLookupBehaviour() {
        // Lookup by name/value
        withKmipSpec(
                KmipSpec.UnknownVersion,
                () -> {
                    DigitalSignatureAlgorithm.Value byName = DigitalSignatureAlgorithm.fromName("X-Enum-Custom");
                    DigitalSignatureAlgorithm.Value byVal = DigitalSignatureAlgorithm.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> DigitalSignatureAlgorithm.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in DigitalSignatureAlgorithm requires 8XXXXXXX (hex) range per implementation
        DigitalSignatureAlgorithm.Value custom = DigitalSignatureAlgorithm.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> DigitalSignatureAlgorithm.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DigitalSignatureAlgorithm.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DigitalSignatureAlgorithm.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DigitalSignatureAlgorithm.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}