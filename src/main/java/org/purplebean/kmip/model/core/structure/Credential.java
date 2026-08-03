package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.CredentialValue;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.CredentialType;

/**
 * KMIP Credential attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Credential implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.CREDENTIAL.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Credential.class);
    }
  }

  @NonNull
  private final CredentialType credentialType;

  @NonNull
  private final CredentialValue credentialValue;

  @Builder
  private Credential(
      @NonNull CredentialType credentialType,
      @NonNull CredentialValue credentialValue
  ) {
    this.credentialType = credentialType;
    this.credentialValue = credentialValue;
    validate();
  }

  /**
   * Returns the {@link Credential} instance wrapping the given value.
   */
  public static Credential of(
      @NonNull CredentialType credentialType,
      @NonNull CredentialValue credentialValue
  ) {
    return Credential
        .builder()
        .credentialType(credentialType)
        .credentialValue(credentialValue)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(credentialType, "CredentialType cannot be null");
    Objects.requireNonNull(credentialValue, "CredentialValue cannot be null");
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
        .of(credentialType, credentialValue)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
