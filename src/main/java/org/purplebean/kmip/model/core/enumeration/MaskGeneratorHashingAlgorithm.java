package org.purplebean.kmip.model.core.enumeration;

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

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * hashing algorithm to be used by the Mask Generation Function referenced in
 * {@link MaskGenerator}, typically as part of an RSA-OAEP or RSA-PSS
 * {@code CryptographicParameters} structure.
 *
 * <p>This type is a thin wrapper around {@link HashingAlgorithm}: it reuses the exact same
 * set of {@link HashingAlgorithm.Value} constants (e.g. {@code SHA_256}) but is registered
 * under its own KMIP tag, {@code MaskGeneratorHashingAlgorithm}, as required by the KMIP
 * specification.
 *
 * @see KmipEnumeration
 * @see HashingAlgorithm
 * @see MaskGenerator
 */
@Data
@Builder(toBuilder = true)
public class MaskGeneratorHashingAlgorithm implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.MASK_GENERATOR_HASHING_ALGORITHM.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          MaskGeneratorHashingAlgorithm.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), MaskGeneratorHashingAlgorithm::fromName,
          MaskGeneratorHashingAlgorithm::fromValue);
    }
  }

  @NonNull
  private final HashingAlgorithm.Value value;

  @Builder
  private MaskGeneratorHashingAlgorithm(@NonNull HashingAlgorithm.Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link MaskGeneratorHashingAlgorithm} instance wrapping the given value.
   */
  public static MaskGeneratorHashingAlgorithm of(@NonNull HashingAlgorithm.Value value) {
    return new MaskGeneratorHashingAlgorithm(value);
  }

  /**
   * Look up by name, delegating to {@link HashingAlgorithm}'s registry since this type reuses
   * {@link HashingAlgorithm.Value} constants.
   */
  public static HashingAlgorithm.Value fromName(String name) {
    return HashingAlgorithm.fromName(name);
  }

  /**
   * Look up by value, delegating to {@link HashingAlgorithm}'s registry since this type reuses
   * {@link HashingAlgorithm.Value} constants.
   */
  public static HashingAlgorithm.Value fromValue(int value) {
    return HashingAlgorithm.fromValue(value);
  }

  private void validate() {
    KmipSpec spec = KmipContext.getSpec();
    if (!value.isSupported()) {
      throw new IllegalArgumentException(
          String.format("Value '%s' for MaskGeneratorHashingAlgorithm is not supported for "
              + "KMIP spec %s", value.getDescription(), spec)
      );
    }
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
  public String getDescription() {
    return value.getDescription();
  }

  /**
   * Returns whether this enumeration value is a custom vendor extension.
   */
  public boolean isCustom() {
    return value.isCustom();
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && value.isSupported();
  }

  @Override
  public int getIntValue() {
    return value.getValue();
  }
}
