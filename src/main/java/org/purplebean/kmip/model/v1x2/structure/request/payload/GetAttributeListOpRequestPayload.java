package org.purplebean.kmip.model.v1x2.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP GetAttributeListOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class GetAttributeListOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.GET_ATTRIBUTE_LIST;
  private static final Set<KmipSpec> supportedVersions = Set.of(
      KmipSpec.UnknownVersion,
      KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4,
      KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          GetAttributeListOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, GetAttributeListOpRequestPayload.class,
          GetAttributeListOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @Builder
  private GetAttributeListOpRequestPayload(
      UniqueIdentifier uniqueIdentifier
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    validate();
  }

  /**
   * Returns the {@link GetAttributeListOpRequestPayload} instance wrapping the given value.
   */
  public static GetAttributeListOpRequestPayload of(List<KmipDataType> values) {
    var builder = GetAttributeListOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
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
            uniqueIdentifier)
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