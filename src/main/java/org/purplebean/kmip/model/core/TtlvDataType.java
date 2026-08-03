package org.purplebean.kmip.model.core;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP TtlvDataType dataType.
 */
@Data
@Builder(toBuilder = true)
public class TtlvDataType implements KmipDataType {

  @NonNull
  private final KmipTag kmipTag;
  @NonNull
  private final EncodingType encodingType;
  @NonNull
  private final Object value;

  @Builder
  private TtlvDataType(
      @NonNull KmipTag kmipTag,
      @NonNull EncodingType encodingType,
      @NonNull Object value
  ) {
    this.value = value;
    this.kmipTag = kmipTag;
    this.encodingType = encodingType;
    validate();

    for (KmipSpec spec : kmipTag.getSupportedVersions()) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion ||
          !kmipTag.isCustom()) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, TtlvDataType.class);
    }
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a Structure value.
   */
  public static TtlvDataType ofStructure(KmipTag.Value kmipTag, List<KmipDataType> kmipDataTypes) {
    return ofStructure(kmipTag, kmipDataTypes.toArray(KmipDataType[]::new));
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a Structure value.
   */
  public static TtlvDataType ofStructure(KmipTag.Value kmipTag, KmipDataType... kmipDataTypes) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.STRUCTURE)
        .value(kmipDataTypes)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a Integer value.
   */
  public static TtlvDataType ofInteger(KmipTag.Value kmipTag, Integer value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a LongInteger value.
   */
  public static TtlvDataType ofLongInteger(KmipTag.Value kmipTag, Long value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.LONG_INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a BigInteger value.
   */
  public static TtlvDataType ofBigInteger(KmipTag.Value kmipTag, BigInteger value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.BIG_INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a Enumeration value.
   */
  public static TtlvDataType ofEnumeration(KmipTag.Value kmipTag, KmipEnumeration.Value<?> value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.ENUMERATION)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a Boolean value.
   */
  public static TtlvDataType ofBoolean(KmipTag.Value kmipTag, Boolean value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.BOOLEAN)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a TextString value.
   */
  public static TtlvDataType ofTextString(KmipTag.Value kmipTag, String value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.TEXT_STRING)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a ByteString value.
   */
  public static TtlvDataType ofByteString(KmipTag.Value kmipTag, ByteBuffer value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.BYTE_STRING)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a ByteString value.
   */
  public static TtlvDataType ofByteString(KmipTag.Value kmipTag, byte[] value) {
    return ofByteString(kmipTag, ByteBuffer.wrap(value));
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a DateTime value.
   */
  public static TtlvDataType ofDateTime(KmipTag.Value kmipTag, OffsetDateTime value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
        .encodingType(EncodingType.DATE_TIME)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link TtlvDataType} wrapping a Interval value.
   */
  public static TtlvDataType ofInterval(KmipTag.Value kmipTag, Integer value) {
    return TtlvDataType
        .builder()
        .kmipTag(kmipTag.inst())
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
  public boolean isSupported() {
    return kmipTag.isSupported();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof KmipDataType that)) {
      return false;
    }
    return Objects.equals(kmipTag, that.getKmipTag()) &&
        encodingType == that.getEncodingType() &&
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