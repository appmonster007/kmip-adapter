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
 * KMIP DeactivationMessage dataType (TextString).
 *
 * <p>Introduced in KMIP v3.0. Carries a human-readable deactivation reason message.</p>
 */
@Data
@Builder(toBuilder = true)
public class DeactivationMessage implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.DEACTIVATION_MESSAGE.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeactivationMessage.class);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private DeactivationMessage(@NonNull String value) {
    this.value = value;
    validate();
  }

  public static DeactivationMessage of(@NonNull String value) {
    return new DeactivationMessage(value);
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
