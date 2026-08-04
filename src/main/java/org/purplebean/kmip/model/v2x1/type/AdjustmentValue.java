package org.purplebean.kmip.model.v2x1.type;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP AdjustmentValue dataType (V2_1+, tag 0x420162).
 *
 * <p>Used by the {@code AdjustAttribute} request payload to carry the value for the
 * requested adjustment (e.g. the increment/decrement amount). Per §6.1.3, the spec does not
 * pin this field to a single wire type; it is encoded according to the underlying type of the
 * attribute being adjusted (e.g. Integer, LongInteger, Interval, DateTime), mirroring the
 * polymorphic pattern used by {@link org.purplebean.kmip.model.core.type.AttributeValue}.
 */
@Data
@Builder(toBuilder = true)
public class AdjustmentValue implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.ADJUSTMENT_VALUE.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      for (EncodingType encoding : EncodingType.values()) {
        KmipDataType.register(spec, kmipTag.getValue(), encoding, AdjustmentValue.class);
      }
    }
  }

  private final EncodingType encodingType;
  @NonNull
  private final Object value;

  @Builder
  private AdjustmentValue(EncodingType encodingType, @NonNull Object value) {
    this.encodingType = encodingType;
    this.value = value;
    validate();
  }

  /**
   * Creates a {@link AdjustmentValue} wrapping an Integer value.
   */
  public static AdjustmentValue ofInteger(Integer value) {
    return AdjustmentValue
        .builder()
        .encodingType(EncodingType.INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AdjustmentValue} wrapping a LongInteger value.
   */
  public static AdjustmentValue ofLongInteger(Long value) {
    return AdjustmentValue
        .builder()
        .encodingType(EncodingType.LONG_INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AdjustmentValue} wrapping a BigInteger value.
   */
  public static AdjustmentValue ofBigInteger(BigInteger value) {
    return AdjustmentValue
        .builder()
        .encodingType(EncodingType.BIG_INTEGER)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AdjustmentValue} wrapping an Interval value.
   */
  public static AdjustmentValue ofInterval(Integer value) {
    return AdjustmentValue
        .builder()
        .encodingType(EncodingType.INTERVAL)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AdjustmentValue} wrapping a DateTime value.
   */
  public static AdjustmentValue ofDateTime(OffsetDateTime value) {
    return AdjustmentValue
        .builder()
        .encodingType(EncodingType.DATE_TIME)
        .value(value)
        .build();
  }

  /**
   * Creates a {@link AdjustmentValue} wrapping a ByteString value.
   */
  public static AdjustmentValue ofByteString(ByteBuffer value) {
    return AdjustmentValue
        .builder()
        .encodingType(EncodingType.BYTE_STRING)
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
}
