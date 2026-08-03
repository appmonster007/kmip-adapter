package org.purplebean.kmip.model.v3x0.type;

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
 * KMIP {@code PrivateKeyUniqueIdentifier} dataType ({@code 0x420066}), encoded as an {@code
 * Identifier}
 * (KMIP 3.0 {@code §4.68} only — same underlying bytes as {@code TextString} but a distinct TTLV
 * Item Type).
 * <p>
 * Unrelated to {@link org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier} (the pre-3
 * .0 {@code TextString}
 * form) — the two are separate Java types that happen to share a KMIP tag and value shape; no
 * single KMIP message
 * ever needs both, so this is a per-spec-version fork rather than a polymorphic interface.
 */
@Data
@Builder(toBuilder = true)
public class PrivateKeyUniqueIdentifier implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER.inst();
  public static final EncodingType encodingType = EncodingType.IDENTIFIER;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          PrivateKeyUniqueIdentifier.class);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private PrivateKeyUniqueIdentifier(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link PrivateKeyUniqueIdentifier} instance wrapping the given value.
   */
  public static PrivateKeyUniqueIdentifier of(@NonNull String value) {
    return new PrivateKeyUniqueIdentifier(value);
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
    return supportedVersions.contains(spec);
  }
}
