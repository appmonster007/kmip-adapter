package org.purplebean.kmip.model.core.structure;

import java.util.List;
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
 * KMIP MACSignatureKeyInformation attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class MACSignatureKeyInformation implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          MACSignatureKeyInformation.class);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;
  private final CryptographicParameters cryptographicParameters;

  @Builder
  private MACSignatureKeyInformation(@NonNull UniqueIdentifier uniqueIdentifier,
                                     CryptographicParameters cryptographicParameters) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.cryptographicParameters = cryptographicParameters;
    validate();
  }

  /**
   * Returns the {@link MACSignatureKeyInformation} instance wrapping the given value.
   */
  public static MACSignatureKeyInformation of(@NonNull UniqueIdentifier uniqueIdentifier,
                                              CryptographicParameters cryptographicParameters) {
    return MACSignatureKeyInformation
        .builder()
        .uniqueIdentifier(uniqueIdentifier)
        .cryptographicParameters(cryptographicParameters)
        .build();
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(uniqueIdentifier, cryptographicParameters)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
