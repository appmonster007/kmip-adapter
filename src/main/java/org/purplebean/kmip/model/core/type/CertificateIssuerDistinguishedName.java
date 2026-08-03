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
 * KMIP CertificateIssuerDistinguishedName dataType.
 */
@Data
@Builder(toBuilder = true)
public class CertificateIssuerDistinguishedName implements KmipDataType {

  public static final KmipTag kmipTag =
      KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CertificateIssuerDistinguishedName.class);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private CertificateIssuerDistinguishedName(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link CertificateIssuerDistinguishedName} instance wrapping the given value.
   */
  public static CertificateIssuerDistinguishedName of(@NonNull String value) {
    return new CertificateIssuerDistinguishedName(value);
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
