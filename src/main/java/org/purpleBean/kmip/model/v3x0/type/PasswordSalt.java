package org.purpleBean.kmip.model.v3x0.type;

import java.nio.ByteBuffer;
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
 * KMIP PasswordSalt dataType (ByteString).
 *
 * <p>Introduced in KMIP v3.0. Carries the salt bytes used when hashing a password.</p>
 */
@Data
@Builder(toBuilder = true)
public class PasswordSalt implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.PASSWORD_SALT.inst();
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PasswordSalt.class);
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private PasswordSalt(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  public static PasswordSalt of(@NonNull ByteBuffer value) {
    return new PasswordSalt(value);
  }

  public static PasswordSalt of(byte[] value) {
    return PasswordSalt
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
