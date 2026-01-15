package org.purpleBean.kmip.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationAttributeTestSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CryptographicAlgorithm Domain Tests")
class CryptographicAlgorithmTest extends AbstractKmipEnumerationAttributeTestSuite<CryptographicAlgorithm> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CryptographicAlgorithm> type() {
        return CryptographicAlgorithm.class;
    }

    @Override
    protected CryptographicAlgorithm createDefault() {
        return CryptographicAlgorithm.Standard.DES.inst();
    }

    @Override
    protected CryptographicAlgorithm createEqualToDefault() {
        return CryptographicAlgorithm.Standard.DES.inst();
    }

    @Override
    protected CryptographicAlgorithm createDifferentFromDefault() {
        return CryptographicAlgorithm.Standard.TRIPLE_DES.inst();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }

    @Override
    protected boolean supportsRegistryBehavior() {
        return true;
    }

    // Implementation of AbstractKmipDataTypeAttributeTestSuite methods
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
        return false;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return null; // Not modifiable by server in any state
    }

    @Override
    public State stateForServerModifiableFalse() {
        return State.Standard.PRE_ACTIVE.inst(); // Any state would work since it's not modifiable
    }

    @Override
    public State stateForClientModifiableTrue() {
        return null; // Not modifiable by client in any state
    }

    @Override
    public State stateForClientModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // Any state would work since it's not modifiable
    }

    @Override
    protected void attrEnum_serverModifiable_respectsState() {
        // Not applicable as it's not server modifiable
    }

    @Override
    protected void attrEnum_clientModifiable_respectsState() {
        // Not applicable as it's not client modifiable
    }

    @Override
    protected void assertLookupBehaviour() {
        // Lookup by name/value
        withKmipSpec(
                KmipSpec.UnknownVersion,
                () -> {
                    CryptographicAlgorithm.Value byName = CryptographicAlgorithm.fromName("X-Enum-Custom");
                    CryptographicAlgorithm.Value byVal = CryptographicAlgorithm.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        // Lookup by name/value with unsupported version
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> CryptographicAlgorithm.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in CryptographicAlgorithm requires 8XXXXXXX (hex) range per implementation
        CryptographicAlgorithm.Value custom = CryptographicAlgorithm.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> CryptographicAlgorithm.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CryptographicAlgorithm.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CryptographicAlgorithm.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CryptographicAlgorithm.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

