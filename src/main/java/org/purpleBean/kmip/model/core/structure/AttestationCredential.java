package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.type.AttestationAssertion;
import org.purplebean.kmip.model.core.type.AttestationMeasurement;

@Data
@Builder(toBuilder = true)
public class AttestationCredential implements CredentialValue, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttestationCredential.class);
      CredentialValue.register(spec, encodingType, CredentialType.Standard.ATTESTATION,
          AttestationCredential.class, AttestationCredential::of);
    }
  }

  @NonNull
  private final Nonce nonce;

  @NonNull
  private final AttestationType attestationType;

  private final AttestationMeasurement attestationMeasurement;

  private final AttestationAssertion attestationAssertion;

  @Builder
  private AttestationCredential(
      @NonNull Nonce nonce,
      @NonNull AttestationType attestationType,
      AttestationMeasurement attestationMeasurement,
      AttestationAssertion attestationAssertion
  ) {
    this.nonce = nonce;
    this.attestationType = attestationType;
    this.attestationMeasurement = attestationMeasurement;
    this.attestationAssertion = attestationAssertion;
    validate();
  }

  public static AttestationCredential of(CredentialValue value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid credential value: " + value);
    }
    return of(structure.getValue());
  }

  public static AttestationCredential of(KmipDataType... values) {
    return of(List.of(values));
  }

  public static AttestationCredential of(List<KmipDataType> values) {
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    AttestationCredentialBuilder builder = AttestationCredential.builder();
    if (map.containsKey(Nonce.kmipTag)) {
      builder.nonce((Nonce) map
          .get(Nonce.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttestationType.kmipTag)) {
      builder.attestationType((AttestationType) map
          .get(AttestationType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttestationMeasurement.kmipTag)) {
      builder.attestationMeasurement((AttestationMeasurement) map
          .get(AttestationMeasurement.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttestationAssertion.kmipTag)) {
      builder.attestationAssertion((AttestationAssertion) map
          .get(AttestationAssertion.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(nonce, "Nonce cannot be null");
    Objects.requireNonNull(attestationType, "AttestationType cannot be null");
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
        .of(
            nonce,
            attestationType,
            attestationMeasurement,
            attestationAssertion)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
