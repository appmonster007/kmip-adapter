package org.purpleBean.kmip.model.v2x1.type;

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
 * KMIP InteropIdentifier dataType — TextString carrying the interop identifier for the Interop
 * operation.
 * Tag: INTEROP_IDENTIFIER (0x420161), supported V2_1 and V3_0.
 */
@Data
@Builder(toBuilder = true)
public class InteropIdentifier implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.INTEROP_IDENTIFIER.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, InteropIdentifier.class);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private InteropIdentifier(@NonNull String value) {
    this.value = value;
    validate();
  }

  public static InteropIdentifier of(@NonNull String value) {
    return new InteropIdentifier(value);
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
