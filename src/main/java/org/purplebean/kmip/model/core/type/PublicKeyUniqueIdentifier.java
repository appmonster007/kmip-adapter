package org.purplebean.kmip.model.core.type;

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
 * KMIP {@code PublicKeyUniqueIdentifier} dataType ({@code 0x42006F}), encoded as a {@code
 * TextString} (v1.2-v2.1).
 *
 * <p>Sibling of {@link org.purplebean.kmip.model.v3x0.type.PublicKeyUniqueIdentifier} (KMIP 3
 * .0-only {@code Identifier}
 * wire type) — the two are unrelated Java types that happen to share a KMIP tag and value shape.
 */
@Data
@Builder(toBuilder = true)
public class PublicKeyUniqueIdentifier implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          PublicKeyUniqueIdentifier.class);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private PublicKeyUniqueIdentifier(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link PublicKeyUniqueIdentifier} instance wrapping the given value.
   */
  public static PublicKeyUniqueIdentifier of(@NonNull String value) {
    return new PublicKeyUniqueIdentifier(value);
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
