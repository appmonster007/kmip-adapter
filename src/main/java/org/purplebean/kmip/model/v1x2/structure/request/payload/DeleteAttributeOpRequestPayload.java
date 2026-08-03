package org.purplebean.kmip.model.v1x2.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.AttributeIndex;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP DeleteAttributeOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class DeleteAttributeOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.DELETE_ATTRIBUTE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          DeleteAttributeOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, DeleteAttributeOpRequestPayload.class,
          DeleteAttributeOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final AttributeName attributeName;

  private final AttributeIndex attributeIndex;

  @Builder
  private DeleteAttributeOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      @NonNull AttributeName attributeName,
      AttributeIndex attributeIndex
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.attributeName = attributeName;
    this.attributeIndex = attributeIndex;
    validate();
  }

  /**
   * Returns the {@link DeleteAttributeOpRequestPayload} instance wrapping the given value.
   */
  public static DeleteAttributeOpRequestPayload of(List<KmipDataType> values) {
    var builder = DeleteAttributeOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttributeName.kmipTag)) {
      builder.attributeName((AttributeName) map
          .get(AttributeName.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttributeIndex.kmipTag)) {
      builder.attributeIndex((AttributeIndex) map
          .get(AttributeIndex.kmipTag)
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
            uniqueIdentifier,
            attributeName,
            attributeIndex)
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