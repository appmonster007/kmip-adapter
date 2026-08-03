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
 * KMIP HashedPasswordUsername dataType (ByteString).
 *
 * <p>Introduced in KMIP v3.0. Carries the hashed value of password+username.</p>
 */
@Data
@Builder(toBuilder = true)
public class HashedPasswordUsername implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.HASHED_PASSWORD_USERNAME.inst();
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, HashedPasswordUsername.class);
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private HashedPasswordUsername(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link HashedPasswordUsername} instance wrapping the given value.
   */
  public static HashedPasswordUsername of(@NonNull ByteBuffer value) {
    return new HashedPasswordUsername(value);
  }

  /**
   * Returns the {@link HashedPasswordUsername} instance wrapping the given value.
   */
  public static HashedPasswordUsername of(byte[] value) {
    return HashedPasswordUsername
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
