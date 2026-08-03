package org.purplebean.kmip.model.v1x2.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.ValidityDate;

/**
 * KMIP ValidateOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class ValidateOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.VALIDATE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ValidateOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, ValidateOpRequestPayload.class,
          ValidateOpRequestPayload::of);
    }
  }

  @Singular
  private final List<Certificate> certificates;

  @Singular
  private final List<UniqueIdentifier> uniqueIdentifiers;

  private final ValidityDate validityDate;

  @Builder
  private ValidateOpRequestPayload(
      List<Certificate> certificates,
      List<UniqueIdentifier> uniqueIdentifiers,
      ValidityDate validityDate
  ) {
    this.certificates = certificates;
    this.uniqueIdentifiers = uniqueIdentifiers;
    this.validityDate = validityDate;
    validate();
  }

  /**
   * Returns the {@link ValidateOpRequestPayload} instance wrapping the given value.
   */
  public static ValidateOpRequestPayload of(List<KmipDataType> values) {
    var builder = ValidateOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Certificate.kmipTag)) {
      map
          .get(Certificate.kmipTag)
          .forEach(item -> builder.certificate((Certificate) item));
    }
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      map
          .get(UniqueIdentifier.kmipTag)
          .forEach(item -> builder.uniqueIdentifier((UniqueIdentifier) item));
    }
    if (map.containsKey(ValidityDate.kmipTag)) {
      builder.validityDate((ValidityDate) map
          .get(ValidityDate.kmipTag)
          .getFirst());
    }
    return builder.build();
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
        .of(
            certificates,
            uniqueIdentifiers,
            validityDate)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}