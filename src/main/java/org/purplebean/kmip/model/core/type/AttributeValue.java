package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipMaskType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP AttributeValue dataType.
 */
@Data
@Builder(toBuilder = true)
public class AttributeValue implements KmipDataType, KmipMaskType {

  public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE_VALUE.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      for (EncodingType encodingType : EncodingType.values()) {
        KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValue.class);
      }
    }
  }

  private final EncodingType encodingType;
  @NonNull
  private final Object value;
  private final String maskString;

  @Builder
  private AttributeValue(EncodingType encodingType, @NonNull Object value, String maskString) {
    this.encodingType = encodingType;
    this.value = value;
    this.maskString = maskString;
    validate();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a Structure value.
   */
  public static AttributeValue ofStructure(List<KmipDataType> kmipDataTypes) {
    return ofStructure(kmipDataTypes.toArray(KmipDataType[]::new));
  }

  /**
   * Creates a {@link AttributeValue} wrapping a Structure value.
   */
  public static AttributeValue ofStructure(KmipDataType... kmipDataTypes) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.STRUCTURE)
        .value(kmipDataTypes)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a Integer value.
   */
  public static AttributeValue ofInteger(Integer value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a MaskInteger value.
   */
  public static AttributeValue ofMaskInteger(Integer value, String maskString) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.INTEGER)
        .value(value)
        .maskString(maskString)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a LongInteger value.
   */
  public static AttributeValue ofLongInteger(Long value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.LONG_INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a BigInteger value.
   */
  public static AttributeValue ofBigInteger(BigInteger value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.BIG_INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a Enumeration value.
   */
  public static AttributeValue ofEnumeration(KmipEnumeration.Value<?> value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.ENUMERATION)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a Boolean value.
   */
  public static AttributeValue ofBoolean(Boolean value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.BOOLEAN)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a TextString value.
   */
  public static AttributeValue ofTextString(String value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.TEXT_STRING)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a ByteString value.
   */
  public static AttributeValue ofByteString(ByteBuffer value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.BYTE_STRING)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a ByteString value.
   */
  public static AttributeValue ofByteString(byte[] value) {
    return ofByteString(ByteBuffer.wrap(value));
  }

  /**
   * Creates a {@link AttributeValue} wrapping a DateTime value.
   */
  public static AttributeValue ofDateTime(OffsetDateTime value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.DATE_TIME)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AttributeValue} wrapping a Interval value.
   */
  public static AttributeValue ofInterval(Integer value) {
    return AttributeValue
        .builder()
        .encodingType(EncodingType.INTERVAL)
        .value(value)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    if (!encodingType
        .getClazz()
        .isInstance(value)) {
      throw new IllegalArgumentException(
          String.format("Invalid value for %s: %s", KmipContext.getSpec(), value));
    }
    // No validation needed for this structure
  }

  @Override
  public KmipTag getKmipTag() {
    return kmipTag;
  }

  @Override
  public EncodingType getEncodingType() {
    return encodingType;
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof KmipDataType that)) {
      return false;
    }
    return encodingType == that.getEncodingType() &&
        deepEqualsValue(this.value, that.getValue());
  }

  private boolean deepEqualsValue(Object v1, Object v2) {
    if (v1 == v2) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }

    // Handle KmipDataType[] arrays
    if (v1 instanceof KmipDataType[] a1 && v2 instanceof KmipDataType[] a2) {
      if (a1.length != a2.length) {
        return false;
      }
      List<KmipDataType> list = new ArrayList<>(Arrays.asList(a2));
      for (KmipDataType o : a1) {
        if (!list.remove(o)) {
          return false;
        }
      }
      return true;
    }

    if (v1 instanceof OffsetDateTime d1 && v2 instanceof OffsetDateTime d2) {
      return Objects.equals(
          d1
              .withNano(0)
              .atZoneSameInstant(ZoneOffset.UTC),
          d2
              .withNano(0)
              .atZoneSameInstant(ZoneOffset.UTC)
      );
    }
    // Fallback to standard equals (for other types)
    return Objects.equals(v1, v2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(kmipTag, encodingType, hashCodeValue(value));
  }

  private int hashCodeValue(Object value) {
    if (value instanceof KmipDataType[] kmipDataTypes) {
      int result = 0;
      for (KmipDataType element : kmipDataTypes) {
        result += Objects.hashCode(element);
      }
      return result;
    }
    return Objects.hashCode(value);
  }
}