package org.purplebean.kmip.model.v2x1.structure.request.payload;

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
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP ExportOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class ExportOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.EXPORT;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ExportOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, ExportOpRequestPayload.class,
          ExportOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  private final KeyWrappingSpecification keyWrappingSpecification;

  @Builder
  private ExportOpRequestPayload(UniqueIdentifier uniqueIdentifier,
                                 KeyWrappingSpecification keyWrappingSpecification) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.keyWrappingSpecification = keyWrappingSpecification;
    validate();
  }

  /**
   * Returns the {@link ExportOpRequestPayload} instance wrapping the given value.
   */
  public static ExportOpRequestPayload of(List<KmipDataType> values) {
    var builder = ExportOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(KeyWrappingSpecification.kmipTag)) {
      builder.keyWrappingSpecification((KeyWrappingSpecification) map
          .get(KeyWrappingSpecification.kmipTag)
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
        .of(uniqueIdentifier, keyWrappingSpecification)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
