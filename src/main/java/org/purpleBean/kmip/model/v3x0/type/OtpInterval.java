package org.purpleBean.kmip.model.v3x0.type;

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
 * KMIP OtpInterval dataType (Interval).
 *
 * <p>Introduced in KMIP v3.0. Specifies the time step interval (in seconds) for TOTP.</p>
 * <p>Uses KMIP {@link EncodingType#INTERVAL} (4-byte unsigned integer stored as Integer).</p>
 */
@Data
@Builder(toBuilder = true)
public class OtpInterval implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.OTP_INTERVAL.inst();
  public static final EncodingType encodingType = EncodingType.INTERVAL;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, OtpInterval.class);
    }
  }

  @NonNull
  private final Integer value;

  @Builder
  private OtpInterval(@NonNull Integer value) {
    this.value = value;
    validate();
  }

  public static OtpInterval of(@NonNull Integer value) {
    return new OtpInterval(value);
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
