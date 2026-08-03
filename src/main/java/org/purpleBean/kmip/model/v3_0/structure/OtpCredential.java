package org.purpleBean.kmip.model.v3_0.structure;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.v3_0.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.model.v3_0.type.OtpCounter;
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;
import org.purpleBean.kmip.model.v3_0.type.OtpInterval;
import org.purpleBean.kmip.model.v3_0.type.OtpSeed;
import org.purpleBean.kmip.model.v3_0.type.OtpSerial;

/**
 * KMIP OtpCredential structure (KMIP v3.0).
 *
 * <p>Encodes a One-Time Password credential per KMIP v3.0 spec.</p>
 *
 * <ul>
 *   <li>{@code otpAlgorithm} — required Enumeration (tag OTP_ALGORITHM 0x4201A8)</li>
 *   <li>{@code otpDigest}    — optional Enumeration wrapping CryptographicAlgorithm (tag
 *   OTP_DIGEST 0x4201A9)</li>
 *   <li>{@code otpSerial}    — optional TextString (tag OTP_SERIAL 0x4201AA)</li>
 *   <li>{@code otpSeed}      — optional ByteString (tag OTP_SEED 0x4201AB)</li>
 *   <li>{@code otpInterval}  — optional Interval (tag OTP_INTERVAL 0x4201AC)</li>
 *   <li>{@code otpDigits}    — optional Integer (tag OTP_DIGITS 0x4201AD)</li>
 *   <li>{@code otpCounter}   — optional Integer (tag OTP_COUNTER 0x4201AE)</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class OtpCredential implements KmipStructure {

  public static final KmipTag kmipTag = KmipTag.Standard.OTP_CREDENTIAL.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, OtpCredential.class);
    }
  }

  @NonNull
  private final OtpAlgorithm otpAlgorithm;

  private final OtpDigest otpDigest;
  private final OtpSerial otpSerial;
  private final OtpSeed otpSeed;
  private final OtpInterval otpInterval;
  private final OtpDigits otpDigits;
  private final OtpCounter otpCounter;

  @Builder
  private OtpCredential(
      @NonNull OtpAlgorithm otpAlgorithm,
      OtpDigest otpDigest,
      OtpSerial otpSerial,
      OtpSeed otpSeed,
      OtpInterval otpInterval,
      OtpDigits otpDigits,
      OtpCounter otpCounter) {
    this.otpAlgorithm = otpAlgorithm;
    this.otpDigest = otpDigest;
    this.otpSerial = otpSerial;
    this.otpSeed = otpSeed;
    this.otpInterval = otpInterval;
    this.otpDigits = otpDigits;
    this.otpCounter = otpCounter;
    validate();
  }

  public static OtpCredential of(
      @NonNull OtpAlgorithm otpAlgorithm,
      OtpDigest otpDigest,
      OtpSerial otpSerial,
      OtpSeed otpSeed,
      OtpInterval otpInterval,
      OtpDigits otpDigits,
      OtpCounter otpCounter) {
    return OtpCredential
        .builder()
        .otpAlgorithm(otpAlgorithm)
        .otpDigest(otpDigest)
        .otpSerial(otpSerial)
        .otpSeed(otpSeed)
        .otpInterval(otpInterval)
        .otpDigits(otpDigits)
        .otpCounter(otpCounter)
        .build();
  }

  public static OtpCredential of(@NonNull OtpAlgorithm otpAlgorithm) {
    return OtpCredential
        .builder()
        .otpAlgorithm(otpAlgorithm)
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
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(otpAlgorithm, otpDigest, otpSerial, otpSeed, otpInterval, otpDigits, otpCounter)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
