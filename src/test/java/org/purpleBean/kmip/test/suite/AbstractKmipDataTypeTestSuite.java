package org.purplebean.kmip.test.suite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.test.BaseKmipTest;

/**
 * Base domain suite for objects implementing KmipDataType.
 * Provides sanity checks common to all KMIP data types.
 */
@DisplayName("Abstract KMIP DataType Suite")
public abstract class AbstractKmipDataTypeTestSuite<T extends KmipDataType> extends BaseKmipTest {

  protected abstract Class<T> type();

  protected abstract T createDefault();

  /**
   * Override if a specific EncodingType is expected (e.g., STRUCTURE, ENUMERATION).
   * If null, this assertion is skipped.
   */
  protected EncodingType expectedEncodingType() {
    return null;
  }

  /**
   * Override when a type is expected to be supported even for KmipSpec.UnsupportedVersion.
   * Defaults to false for strict types.
   */
  protected boolean expectedSupportedForUnsupportedSpec() {
    return false;
  }

  protected void validateTagAndEncodingType() {
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
  @DisplayName("KMIP: has non-null tag and encoding type")
  protected void kmip_hasTagAndEncodingType() {
    validateTagAndEncodingType();
  }

  @Test
  @DisplayName("KMIP: supported for default spec")
  protected void kmip_supportedForDefaultSpec() {
    T obj = createDefault();
    assertThat(obj.isSupported()).isTrue();
  }

  @Test
  @DisplayName("KMIP: not supported for unsupported spec")
  protected void kmip_notSupportedForUnsupportedSpec() {
    T obj = createDefault();
    boolean expected = expectedSupportedForUnsupportedSpec();
    withKmipSpec(
        KmipSpec.UnsupportedVersion,
        () -> assertThat(obj.isSupported()).isEqualTo(expected)
    );
  }
}
