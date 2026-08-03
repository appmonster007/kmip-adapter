package org.purplebean.kmip.model.v2x1.type;

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
 * KMIP SubmissionDate dataType.
 */
@Data
@Builder(toBuilder = true)
public class SubmissionDate implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.SUBMISSION_DATE.inst();
  public static final EncodingType encodingType = EncodingType.DATE_TIME_EXTENDED;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);
      // TODO: Adjust supported versions

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, SubmissionDate.class);
    }
  }

  @NonNull
  private final OffsetDateTime value;

  @Builder
  private SubmissionDate(@NonNull OffsetDateTime value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link SubmissionDate} instance wrapping the given value.
   */
  public static SubmissionDate of(@NonNull OffsetDateTime value) {
    return new SubmissionDate(value);
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
}