package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("KmipTag Tests")
class KmipTagTest {

    @Nested
    @DisplayName("Tag Encoding")
    class TagEncodingTests {
        @Test
        @DisplayName("getTagBytes returns 3 bytes for any standard tag")
        void getTagBytes_returnsThreeBytes() {
            byte[] bytes = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE).getTagBytes();
            assertThat(bytes).hasSize(3);
        }

        @Test
        @DisplayName("getTagHexString returns 8-char string with 0x prefix")
        void getTagHexString_returnsFormattedHex() {
            String hex = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE).getTagHexString();
            assertThat(hex).startsWith("0x").hasSize(8);
        }
    }

    @Nested
    @DisplayName("Version Support")
    class VersionSupportTests {
        @Test
        @DisplayName("isSupportedFor returns true for any spec (including null)")
        void isSupportedFor_alwaysReturnsTrue() {
            KmipTag tag = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE);
            assertThat(tag.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
            assertThat(tag.isSupportedFor(null)).isTrue();
        }
    }

    @Nested
    @DisplayName("Registry Behavior")
    class RegistryBehaviorTests {
        private static final int VALID_EXTENSION = 0x540010;
        private static final String TEST_DESCRIPTION = "X-Test-Tag";
        private static final Set<KmipSpec> TEST_VERSIONS = Set.of(KmipSpec.UnknownVersion);

        @Test
        @DisplayName("register: accepts valid extension range [0x540000, 0x54FFFF]")
        void register_validExtensionRange() {
            KmipTag.Value custom = KmipTag.register(VALID_EXTENSION, TEST_DESCRIPTION, TEST_VERSIONS);
            assertThat(custom.isCustom()).isTrue();
            assertThat(custom.getDescription()).isEqualTo(TEST_DESCRIPTION);
        }

        @Test
        @DisplayName("register: is idempotent for same input")
        void register_idempotent() {
            KmipTag.Value first = KmipTag.register(VALID_EXTENSION, TEST_DESCRIPTION, TEST_VERSIONS);
            KmipTag.Value second = KmipTag.register(VALID_EXTENSION, TEST_DESCRIPTION, TEST_VERSIONS);
            assertThat(second).isEqualTo(first);
        }

        @Test
        @DisplayName("register: rejects value below extension range")
        void register_rejectsValueBelowRange() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> KmipTag.register(0x53FFFF, TEST_DESCRIPTION, TEST_VERSIONS));
        }

        @Test
        @DisplayName("register: rejects value above extension range")
        void register_rejectsValueAboveRange() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> KmipTag.register(0x550000, TEST_DESCRIPTION, TEST_VERSIONS));
        }

        @Test
        @DisplayName("register: rejects empty or blank description")
        void register_rejectsEmptyDescription() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> KmipTag.register(VALID_EXTENSION, "", TEST_VERSIONS));
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> KmipTag.register(VALID_EXTENSION, "   ", TEST_VERSIONS));
        }

        @Test
        @DisplayName("register: rejects empty version set")
        void register_rejectsEmptyVersions() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> KmipTag.register(VALID_EXTENSION, TEST_DESCRIPTION, Set.of()));
        }
    }

    @Nested
    @DisplayName("Lookup Operations")
    class LookupTests {
        private static final String KNOWN_TAG_NAME = "RequestMessage";
        private static final int KNOWN_TAG_VALUE = 0x420078;

        @Test
        @DisplayName("fromName: finds standard tag by name")
        void fromName_findsStandardTag() {
            KmipTag.Value tag = KmipTag.fromName(KmipSpec.UnknownVersion, KNOWN_TAG_NAME);
            assertThat(tag.getValue()).isEqualTo(KNOWN_TAG_VALUE);
        }

        @Test
        @DisplayName("fromName: throws NoSuchElementException for unknown name")
        void fromName_throwsForUnknownName() {
            assertThatExceptionOfType(NoSuchElementException.class)
                    .isThrownBy(() -> KmipTag.fromName(KmipSpec.UnknownVersion, "__NON_EXISTENT__"));
        }

        @Test
        @DisplayName("fromValue: finds standard tag by value")
        void fromValue_findsStandardTag() {
            KmipTag.Value tag = KmipTag.fromValue(KmipSpec.UnknownVersion, KNOWN_TAG_VALUE);
            assertThat(tag.getDescription()).isEqualTo(KNOWN_TAG_NAME);
        }

        @Test
        @DisplayName("fromValue: throws NoSuchElementException for unknown value")
        void fromValue_throwsForUnknownValue() {
            assertThatExceptionOfType(NoSuchElementException.class)
                    .isThrownBy(() -> KmipTag.fromValue(KmipSpec.UnknownVersion, 0x123456));
        }
    }
}
