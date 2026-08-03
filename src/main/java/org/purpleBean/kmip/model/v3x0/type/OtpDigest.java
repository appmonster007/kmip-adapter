package org.purplebean.kmip.model.v3x0.type;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;

/**
 * KMIP OtpDigest dataType (Enumeration).
 *
 * <p>Introduced in KMIP v3.0. Specifies the digest algorithm used in the OTP computation.
 * Uses {@link CryptographicAlgorithm} values but is encoded under its own tag
 * ({@code OTP_DIGEST = 0x4201A9}).</p>
 */
@Data
@Builder(toBuilder = true)
public class OtpDigest implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.OTP_DIGEST.inst();
  public static final EncodingType encodingType = EncodingType.ENUMERATION;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, OtpDigest.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), OtpDigest::fromName, OtpDigest::fromValue);
    }
  }

  @NonNull
  private final CryptographicAlgorithm.Value value;

  @Builder
  private OtpDigest(@NonNull CryptographicAlgorithm.Value value) {
    this.value = value;
    validate();
  }

  public static OtpDigest of(@NonNull CryptographicAlgorithm.Value value) {
    return new OtpDigest(value);
  }

  public static CryptographicAlgorithm.Value fromName(String name) {
    return CryptographicAlgorithm.fromName(name);
  }

  public static CryptographicAlgorithm.Value fromValue(int value) {
    return CryptographicAlgorithm.fromValue(value);
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    if (!value.isSupported()) {
      throw new IllegalArgumentException(
          String.format("Value '%s' for OtpDigest is not supported for KMIP spec %s",
              value.getDescription(), KmipContext.getSpec()));
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
  public Object getValue() {
    return value.getValue();
  }

  @Override
  public boolean isSupported() {
    return supportedVersions.contains(KmipContext.getSpec()) && value.isSupported();
  }
}
