package org.purpleBean.kmip.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DataEnumeration Domain Tests")
class DataEnumerationTest extends AbstractKmipEnumerationTestSuite<DataEnumeration> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<DataEnumeration> type() {
        return DataEnumeration.class;
    }

    @Override
    protected DataEnumeration createDefault() {
        // TODO: Replace with an actual Standard enum value, e.g., DataEnumeration.Standard.SOME_VALUE.inst();
        // For now, using the first available value if any exist.
        if (DataEnumeration.Standard.values().length > 0) {
            return DataEnumeration.Standard.values()[0].inst();
        }
        // Fallback for enums with no predefined Standard values (e.g., during initial generation)
        return DataEnumeration.register(0x80000001, "X-Default-Value", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    protected DataEnumeration createEqualToDefault() {
        // TODO: Replace with an actual Standard enum value equal to the one in createDefault()
        if (DataEnumeration.Standard.values().length > 0) {
            return DataEnumeration.Standard.values()[0].inst();
        }
        return DataEnumeration.register(0x80000001, "X-Default-Value", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    protected DataEnumeration createDifferentFromDefault() {
        // TODO: Replace with an actual Standard enum value different from the one in createDefault()
        if (DataEnumeration.Standard.values().length > 1) {
            return DataEnumeration.Standard.values()[1].inst();
        }
        // Fallback for enums with only one or no predefined Standard values
        return DataEnumeration.register(0x80000002, "X-Variant-Value", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }

    @Override
    protected boolean supportsRegistryBehavior() {
        // Set to true if the enum supports custom extension values and lookup methods
        return true; // Assuming all generated enums will support this
    }

    @Override
    protected void assertLookupBehaviour() {
        // TODO: Customize these assertions based on actual enum values and expected lookup behavior
        // Example:
        withKmipSpec(
                KmipSpec.UnknownVersion,
                () -> {
                    DataEnumeration.Value byName = DataEnumeration.fromName("X-Enum-Custom");
                    DataEnumeration.Value byVal = DataEnumeration.fromValue(0x80000010);
                    assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
                    assertThat(byVal.getValue()).isEqualTo(0x80000010);
                }
        );

        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> DataEnumeration.fromName("X-Enum-Custom"))
        );
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // TODO: Customize these assertions based on actual enum values and expected registry behavior
        // Example:
        DataEnumeration.Value custom = DataEnumeration.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");

        withKmipSpec(KmipSpec.UnknownVersion, () -> {
            assertThat(custom.isSupported()).isTrue();
        });
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            assertThat(custom.isSupported()).isFalse();
        });

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> DataEnumeration.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DataEnumeration.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DataEnumeration.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DataEnumeration.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}