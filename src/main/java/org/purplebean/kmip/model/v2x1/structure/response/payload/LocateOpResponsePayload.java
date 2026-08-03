package org.purplebean.kmip.model.v2x1.structure.response.payload;

import java.util.Collections;
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
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.type.LocatedItems;

@Data
@Builder(toBuilder = true)
public class LocateOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.LOCATE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, LocateOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, LocateOpResponsePayload.class,
          LocateOpResponsePayload::of);
    }
  }

  private final LocatedItems locatedItems;

  @Singular
  private final List<UniqueIdentifier> uniqueIdentifiers;

  @Builder
  private LocateOpResponsePayload(
      LocatedItems locatedItems,
      List<UniqueIdentifier> uniqueIdentifiers
  ) {
    this.locatedItems = locatedItems;
    this.uniqueIdentifiers =
        (uniqueIdentifiers == null) ? Collections.emptyList() : uniqueIdentifiers;
    validate();
  }

  public static LocateOpResponsePayload of(List<KmipDataType> values) {
    var builder = LocateOpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(LocatedItems.kmipTag)) {
      builder.locatedItems((LocatedItems) map
          .get(LocatedItems.kmipTag)
          .getFirst());
    }
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      map
          .get(UniqueIdentifier.kmipTag)
          .forEach(item -> builder.uniqueIdentifier((UniqueIdentifier) item));
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
        .concat(
            Stream.of(locatedItems),
            uniqueIdentifiers.stream())
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
