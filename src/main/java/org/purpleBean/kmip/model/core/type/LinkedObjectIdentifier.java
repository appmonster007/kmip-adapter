package org.purpleBean.kmip.model.core.type;

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
 * KMIP LinkedObjectIdentifier dataType.
 */
@Data
@Builder(toBuilder = true)
public class LinkedObjectIdentifier implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.LINKED_OBJECT_IDENTIFIER.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, LinkedObjectIdentifier.class);
    }
  }


  @NonNull
  private final String value;

  @Builder
  private LinkedObjectIdentifier(@NonNull String value) {
    this.value = value;
    validate();
  }

  public static LinkedObjectIdentifier of(@NonNull String value) {
    return new LinkedObjectIdentifier(value);
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
