package org.purpleBean.kmip.model.v3_0.type;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;

/**
 * KMIP OtpCounter dataType (Integer).
 *
 * <p>Introduced in KMIP v3.0. Carries the HOTP counter value for counter-based OTP.</p>
 */
@Data
@Builder(toBuilder = true)
public class OtpCounter implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.OTP_COUNTER.inst();
  public static final EncodingType encodingType = EncodingType.INTEGER;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, OtpCounter.class);
    }
  }

  @NonNull
  private final Integer value;

  @Builder
  private OtpCounter(@NonNull Integer value) {
    this.value = value;
    validate();
  }

  public static OtpCounter of(@NonNull Integer value) {
    return new OtpCounter(value);
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
    return supportedVersions.contains(KmipContext.getSpec());
  }
}
