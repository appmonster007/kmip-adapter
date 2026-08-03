package org.purpleBean.kmip.model.v2_1.type;

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
 * KMIP InitIndicator dataType.
 */
@Data
@Builder(toBuilder = true)
public class InitIndicator implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.INIT_INDICATOR.inst();
  public static final EncodingType encodingType = EncodingType.BOOLEAN;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, InitIndicator.class);
    }
  }

  @NonNull
  private final Boolean value;

  @Builder
  private InitIndicator(@NonNull Boolean value) {
    this.value = value;
    validate();
  }

  public static InitIndicator of(@NonNull Boolean value) {
    return new InitIndicator(value);
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