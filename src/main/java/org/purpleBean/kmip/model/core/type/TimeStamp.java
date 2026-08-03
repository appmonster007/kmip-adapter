package org.purplebean.kmip.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
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
 * KMIP TimeStamp dataType.
 */
@Data
@Builder(toBuilder = true)
public class TimeStamp implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.TIME_STAMP.inst();
  public static final EncodingType encodingType = EncodingType.DATE_TIME;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, TimeStamp.class);
    }
  }


  @NonNull
  private final OffsetDateTime value;

  @Builder
  private TimeStamp(@NonNull OffsetDateTime value) {
    this.value = value;
    validate();
  }

  public static TimeStamp of(@NonNull OffsetDateTime value) {
    return new TimeStamp(value);
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeStamp that = (TimeStamp) o;
    // Compare OffsetDateTime up to seconds to avoid flakiness
    return this.value
        .withNano(0)
        .atZoneSameInstant(ZoneOffset.UTC)
        .equals(that.value
            .withNano(0)
            .atZoneSameInstant(ZoneOffset.UTC));
  }

  @Override
  public int hashCode() {
    return Objects.hash(value
        .withNano(0)
        .atZoneSameInstant(ZoneOffset.UTC));
  }
}
