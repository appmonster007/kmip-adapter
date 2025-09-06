package org.purpleBean.kmip.test;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;

/**
 * Custom AssertJ assertions for KMIP-specific objects.
 * Provides fluent, readable assertions for domain objects.
 */
public class KmipAssertions extends Assertions {

    public static ProtocolVersionAssert assertThat(ProtocolVersion actual) {
        return new ProtocolVersionAssert(actual);
    }

    public static KmipDataTypeAssert assertThat(KmipDataType actual) {
        return new KmipDataTypeAssert(actual);
    }

    public static KmipTagAssert assertThat(KmipTag actual) {
        return new KmipTagAssert(actual);
    }

    /**
     * Custom assertion for ProtocolVersion objects.
     */
    public static class ProtocolVersionAssert extends AbstractAssert<ProtocolVersionAssert, ProtocolVersion> {

        public ProtocolVersionAssert(ProtocolVersion actual) {
            super(actual, ProtocolVersionAssert.class);
        }

        public ProtocolVersionAssert hasMajorVersion(int expectedMajor) {
            isNotNull();
            if (actual.getMajor() != expectedMajor) {
                failWithMessage("Expected major version <%d> but was <%d>", expectedMajor, actual.getMajor());
            }
            return this;
        }

        public ProtocolVersionAssert hasMinorVersion(int expectedMinor) {
            isNotNull();
            if (actual.getMinor() != expectedMinor) {
                failWithMessage("Expected minor version <%d> but was <%d>", expectedMinor, actual.getMinor());
            }
            return this;
        }

        public ProtocolVersionAssert hasVersion(int expectedMajor, int expectedMinor) {
            return hasMajorVersion(expectedMajor).hasMinorVersion(expectedMinor);
        }

        public ProtocolVersionAssert isCompatibleWith(KmipSpec spec) {
            isNotNull();
            if (!actual.isSupportedFor(spec)) {
                failWithMessage("Expected protocol version to be compatible with <%s> but it was not", spec);
            }
            return this;
        }

        public ProtocolVersionAssert hasValidStructure() {
            isNotNull();
            assertThat(actual.getKmipTag()).isNotNull();
            assertThat(actual.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
            assertThat(actual.getValues()).isNotNull().hasSize(2);
            return this;
        }
    }

    /**
     * Custom assertion for KmipDataType objects.
     */
    public static class KmipDataTypeAssert extends AbstractAssert<KmipDataTypeAssert, KmipDataType> {

        public KmipDataTypeAssert(KmipDataType actual) {
            super(actual, KmipDataTypeAssert.class);
        }

        public KmipDataTypeAssert hasKmipTag(KmipTag.Value expectedTag) {
            isNotNull();
            if (!actual.getKmipTag().getValue().equals(expectedTag)) {
                failWithMessage("Expected KMIP tag <%s> but was <%s>", expectedTag, actual.getKmipTag().getValue());
            }
            return this;
        }

        public KmipDataTypeAssert hasEncodingType(EncodingType expectedType) {
            isNotNull();
            if (!actual.getEncodingType().equals(expectedType)) {
                failWithMessage("Expected encoding type <%s> but was <%s>", expectedType, actual.getEncodingType());
            }
            return this;
        }

        public KmipDataTypeAssert isSupportedFor(KmipSpec spec) {
            isNotNull();
            if (!actual.isSupportedFor(spec)) {
                failWithMessage("Expected data type to be supported for <%s> but it was not", spec);
            }
            return this;
        }

        public KmipDataTypeAssert isNotSupportedFor(KmipSpec spec) {
            isNotNull();
            if (actual.isSupportedFor(spec)) {
                failWithMessage("Expected data type to not be supported for <%s> but it was", spec);
            }
            return this;
        }
    }

    /**
     * Custom assertion for KmipTag objects.
     */
    public static class KmipTagAssert extends AbstractAssert<KmipTagAssert, KmipTag> {

        public KmipTagAssert(KmipTag actual) {
            super(actual, KmipTagAssert.class);
        }

        public KmipTagAssert hasValue(int expectedValue) {
            isNotNull();
            if (actual.getValue().getValue() != expectedValue) {
                failWithMessage("Expected tag value <%d> but was <%d>", expectedValue, actual.getValue().getValue());
            }
            return this;
        }

        public KmipTagAssert hasDescription(String expectedDescription) {
            isNotNull();
            if (!actual.getDescription().equals(expectedDescription)) {
                failWithMessage("Expected tag description <%s> but was <%s>", expectedDescription, actual.getDescription());
            }
            return this;
        }

        public KmipTagAssert hasValidTagBytes() {
            isNotNull();
            byte[] tagBytes = actual.getTagBytes();
            if (tagBytes == null || tagBytes.length != 3) {
                failWithMessage("Expected tag bytes to be 3 bytes long but was <%d>", 
                    tagBytes != null ? tagBytes.length : 0);
            }
            return this;
        }

        public KmipTagAssert hasHexString(String expectedHex) {
            isNotNull();
            String actualHex = actual.getTagHexString();
            if (!actualHex.equals(expectedHex)) {
                failWithMessage("Expected hex string <%s> but was <%s>", expectedHex, actualHex);
            }
            return this;
        }

        public KmipTagAssert isStandard() {
            isNotNull();
            if (actual.getValue().isCustom()) {
                failWithMessage("Expected tag to be standard but it was custom");
            }
            return this;
        }

        public KmipTagAssert isCustom() {
            isNotNull();
            if (!actual.getValue().isCustom()) {
                failWithMessage("Expected tag to be custom but it was standard");
            }
            return this;
        }
    }
}
