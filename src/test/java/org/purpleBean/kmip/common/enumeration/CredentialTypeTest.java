package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("CredentialType Domain Tests")
class CredentialTypeTest extends AbstractKmipEnumerationSuite<CredentialType> {

    @Override
    protected Class<CredentialType> type() { 
        return CredentialType.class; 
    }

    @Override
    protected CredentialType createDefault() { 
        return new CredentialType(CredentialType.Standard.USERNAME_AND_PASSWORD); 
    }

    @Override
    protected CredentialType createEqualToDefault() { 
        return new CredentialType(CredentialType.Standard.USERNAME_AND_PASSWORD); 
    }

    @Override
    protected CredentialType createDifferentFromDefault() { 
        return new CredentialType(CredentialType.Standard.DEVICE); 
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
        CredentialType.Value byName = CredentialType.fromName(KmipSpec.V1_2, "X-Enum-Custom");
        CredentialType.Value byVal = CredentialType.fromValue(KmipSpec.V1_2, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);
    }

    @Override
    protected void assertEnumerationRegistryBehaviorPositive() {
        CredentialType.Value custom = CredentialType.register(
            0x80000010, 
            "X-Enum-Custom",
            Set.of(KmipSpec.V1_2, KmipSpec.V2_0)
        );
        
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.V1_2)).isTrue();
    }

    @Override
    protected void assertEnumerationRegistryBehaviorNegative() {
        // Test invalid extension value range
        assertThatThrownBy(() -> 
            CredentialType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.V1_2)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Extension value must be in vendor range");
            
        // Test empty description
        assertThatThrownBy(() -> 
            CredentialType.register(0x80000011, "   ", Set.of(KmipSpec.V1_2)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Description cannot be empty");

    }
}
