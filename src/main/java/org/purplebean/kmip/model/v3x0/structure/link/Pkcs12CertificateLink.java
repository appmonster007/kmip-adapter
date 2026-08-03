package org.purplebean.kmip.model.v3x0.structure.link;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP Pkcs12CertificateLink link attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs12CertificateLink implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.PKCS_12_CERTIFICATE_LINK.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs12CertificateLink.class);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;

  @Builder
  private Pkcs12CertificateLink(@NonNull UniqueIdentifier uniqueIdentifier) {
    this.uniqueIdentifier = uniqueIdentifier;
    validate();
  }

  /**
   * Returns the {@link Pkcs12CertificateLink} instance wrapping the given value.
   */
  public static Pkcs12CertificateLink of(@NonNull UniqueIdentifier uniqueIdentifier) {
    return Pkcs12CertificateLink
        .builder()
        .uniqueIdentifier(uniqueIdentifier)
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
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(uniqueIdentifier)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
