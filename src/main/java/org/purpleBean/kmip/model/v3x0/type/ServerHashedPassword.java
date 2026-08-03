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
 * KMIP ServerHashedPassword dataType.
 */
@Data
@Builder(toBuilder = true)
public class ServerHashedPassword implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.SERVER_HASHED_PASSWORD.inst();
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);
      // introduced in KMIP 3.0 (Response Header) — extend if present in later specs

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ServerHashedPassword.class);
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private ServerHashedPassword(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  public static ServerHashedPassword of(@NonNull ByteBuffer value) {
    return new ServerHashedPassword(value);
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