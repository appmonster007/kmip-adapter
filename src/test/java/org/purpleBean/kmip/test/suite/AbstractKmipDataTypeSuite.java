package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.test.BaseKmipTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base domain suite for objects implementing KmipDataType.
 * Provides sanity checks common to all KMIP data types.
 */
@DisplayName("Abstract KMIP DataType Suite")
public abstract class AbstractKmipDataTypeSuite<T extends KmipDataType> extends BaseKmipTest {

    protected abstract Class<T> type();

    protected abstract T createDefault();

    /**
     * Override if a specific EncodingType is expected (e.g., STRUCTURE, ENUMERATION).
     * If null, this assertion is skipped.
     */
    protected EncodingType expectedEncodingType() { return null; }

    @Test
    @DisplayName("KMIP: has non-null tag and encoding type")
    void kmip_hasTagAndEncodingType() {
        T obj = createDefault();
        KmipTag tag = obj.getKmipTag();
        EncodingType type = obj.getEncodingType();
        assertThat(tag).isNotNull();
        assertThat(type).isNotNull();
        if (expectedEncodingType() != null) {
            assertThat(type).isEqualTo(expectedEncodingType());
        }
    }

    @Test
    @DisplayName("KMIP: supported for default spec")
    void kmip_supportedForDefaultSpec() {
        T obj = createDefault();
        assertThat(obj.isSupportedFor(defaultSpec)).isTrue();
    }

    @Test
    @DisplayName("KMIP: not supported for unsupported spec")
    void kmip_notSupportedForUnsupportedSpec() {
        T obj = createDefault();
        assertThat(obj.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();
    }
}
