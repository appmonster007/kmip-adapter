package org.purplebean.kmip.model.v2x1.type;

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
 * KMIP ServerCorrelationValue dataType.
 */
@Data
@Builder(toBuilder = true)
public class ServerCorrelationValue implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.SERVER_CORRELATION_VALUE.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);
  // introduced in KMIP 2.1 — extend if present in later specs

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ServerCorrelationValue.class);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private ServerCorrelationValue(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link ServerCorrelationValue} instance wrapping the given value.
   */
  public static ServerCorrelationValue of(@NonNull String value) {
    return new ServerCorrelationValue(value);
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