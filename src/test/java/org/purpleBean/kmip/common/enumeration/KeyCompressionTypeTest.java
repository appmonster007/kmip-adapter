package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("KeyCompressionType Domain Tests")
class KeyCompressionTypeTest extends AbstractKmipEnumerationSuite<KeyCompressionType> {

    @Override
    protected Class<KeyCompressionType> type() { 
        return KeyCompressionType.class; 
    }

    @Override
    protected KeyCompressionType createDefault() { 
        return new KeyCompressionType(KeyCompressionType.Standard.NONE); 
    }

    @Override
    protected KeyCompressionType createEqualToDefault() { 
        return new KeyCompressionType(KeyCompressionType.Standard.NONE); 
    }

    @Override
    protected KeyCompressionType createDifferentFromDefault() { 
        return new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY); 
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
        KeyCompressionType.Value byName = KeyCompressionType.fromName(KmipSpec.V1_2, "X-Enum-Custom");
        KeyCompressionType.Value byVal = KeyCompressionType.fromValue(KmipSpec.V1_2, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);
    }

    @Override
    protected void assertEnumerationRegistryBehaviorPositive() {
        KeyCompressionType.Value custom = KeyCompressionType.register(
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
            KeyCompressionType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.V1_2)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Extension value must be in vendor range");
            
        // Test empty description
        assertThatThrownBy(() -> 
            KeyCompressionType.register(0x80000011, "   ", Set.of(KmipSpec.V1_2)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Description cannot be empty");

    }
}
