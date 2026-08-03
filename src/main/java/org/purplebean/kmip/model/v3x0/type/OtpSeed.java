package org.purplebean.kmip.model.v3x0.type;

import java.nio.ByteBuffer;
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
 * KMIP OtpSeed dataType (ByteString).
 *
 * <p>Introduced in KMIP v3.0. Carries the seed bytes for OTP generation.</p>
 */
@Data
@Builder(toBuilder = true)
public class OtpSeed implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.OTP_SEED.inst();
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, OtpSeed.class);
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private OtpSeed(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link OtpSeed} instance wrapping the given value.
   */
  public static OtpSeed of(@NonNull ByteBuffer value) {
    return new OtpSeed(value);
  }

  /**
   * Returns the {@link OtpSeed} instance wrapping the given value.
   */
  public static OtpSeed of(byte[] value) {
    return OtpSeed
        .builder()
        .value(ByteBuffer.wrap(value))
        .build();
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
