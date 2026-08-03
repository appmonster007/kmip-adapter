package org.purplebean.kmip.model.v1x2.structure.response.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP GetAttributeListOpResponsePayload operation response payload.
 */
@Data
@Builder(toBuilder = true)
public class GetAttributeListOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.GET_ATTRIBUTE_LIST;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          GetAttributeListOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, GetAttributeListOpResponsePayload.class,
          GetAttributeListOpResponsePayload::of);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;

  @Singular
  private final List<AttributeName> attributeNames;

  @Singular
  private final List<KmipDataType> attributeReferences;

  @Builder
  private GetAttributeListOpResponsePayload(
      @NonNull UniqueIdentifier uniqueIdentifier,
      List<AttributeName> attributeNames,
      List<KmipDataType> attributeReferences
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.attributeNames =
        (attributeNames == null) ? java.util.Collections.emptyList() : attributeNames;
    this.attributeReferences =
        (attributeReferences == null) ? java.util.Collections.emptyList() : attributeReferences;
    validate();
  }

  /**
   * Returns the {@link GetAttributeListOpResponsePayload} instance wrapping the given value.
   */
  public static GetAttributeListOpResponsePayload of(List<KmipDataType> values) {
    var builder = GetAttributeListOpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttributeName.kmipTag)) {
      map
          .get(AttributeName.kmipTag)
          .forEach(item -> builder.attributeName((AttributeName) item));
    }
    if (map.containsKey(org.purplebean.kmip.model.v2x1.structure.AttributeReference.kmipTag)) {
      map
          .get(org.purplebean.kmip.model.v2x1.structure.AttributeReference.kmipTag)
          .forEach(builder::attributeReference);
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    if (attributeNames.isEmpty() && attributeReferences.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("Empty attribute list for %s", getKmipTag()));
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
            uniqueIdentifier,
            attributeNames,
            attributeReferences)
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
