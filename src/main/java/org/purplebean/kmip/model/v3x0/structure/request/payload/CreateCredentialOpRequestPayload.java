package org.purplebean.kmip.model.v3x0.structure.request.payload;

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
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.structure.Attributes;

/**
 * KMIP CreateCredentialOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class CreateCredentialOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CREATE_CREDENTIAL;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CreateCredentialOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, CreateCredentialOpRequestPayload.class,
          CreateCredentialOpRequestPayload::of);
    }
  }

  @NonNull
  private final CredentialType credentialType;

  @NonNull
  private final Attributes attributes;

  @NonNull
  private final CredentialValue credentialValue;

  @Builder
  private CreateCredentialOpRequestPayload(
      @NonNull CredentialType credentialType,
      @NonNull Attributes attributes,
      @NonNull CredentialValue credentialValue
  ) {
    this.credentialType = credentialType;
    this.attributes = attributes;
    this.credentialValue = credentialValue;
    validate();
  }

  /**
   * Returns the {@link CreateCredentialOpRequestPayload} instance wrapping the given value.
   */
  public static CreateCredentialOpRequestPayload of(List<KmipDataType> values) {
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    // CredentialType must appear before Attributes and CredentialValue in the encoded stream
    // per KMIP spec
    CredentialType credentialType = (CredentialType) map
        .get(CredentialType.kmipTag)
        .getFirst();
    Attributes attributes = (Attributes) map
        .get(Attributes.kmipTag)
        .getFirst();
    CredentialValue credentialValue = (CredentialValue) map
        .get(CredentialValue.kmipTag)
        .getFirst();
    return CreateCredentialOpRequestPayload
        .builder()
        .credentialType(credentialType)
        .attributes(attributes)
        .credentialValue(credentialValue)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(credentialType, "CredentialType cannot be null");
    Objects.requireNonNull(attributes, "Attributes cannot be null");
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
        .of(credentialType, attributes, credentialValue)
        .filter(Objects::nonNull)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
