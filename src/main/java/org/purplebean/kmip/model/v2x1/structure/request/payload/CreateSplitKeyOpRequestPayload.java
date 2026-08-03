package org.purplebean.kmip.model.v2x1.structure.request.payload;

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
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;

/**
 * KMIP CreateSplitKeyOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class CreateSplitKeyOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CREATE_SPLIT_KEY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CreateSplitKeyOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, CreateSplitKeyOpRequestPayload.class,
          CreateSplitKeyOpRequestPayload::of);
    }
  }

  @NonNull
  private final ObjectType objectType;
  @NonNull
  private final UniqueIdentifier uniqueIdentifier;
  @NonNull
  private final SplitKeyParts splitKeyParts;
  @NonNull
  private final SplitKeyThreshold splitKeyThreshold;
  @NonNull
  private final SplitKeyMethod splitKeyMethod;
  private final Attributes attributes;

  @Builder
  private CreateSplitKeyOpRequestPayload(
      @NonNull ObjectType objectType,
      @NonNull UniqueIdentifier uniqueIdentifier,
      @NonNull SplitKeyParts splitKeyParts,
      @NonNull SplitKeyThreshold splitKeyThreshold,
      @NonNull SplitKeyMethod splitKeyMethod,
      Attributes attributes
  ) {
    this.objectType = objectType;
    this.uniqueIdentifier = uniqueIdentifier;
    this.splitKeyParts = splitKeyParts;
    this.splitKeyThreshold = splitKeyThreshold;
    this.splitKeyMethod = splitKeyMethod;
    this.attributes = attributes;
    validate();
  }

  /**
   * Returns the {@link CreateSplitKeyOpRequestPayload} instance wrapping the given value.
   */
  public static CreateSplitKeyOpRequestPayload of(List<KmipDataType> values) {
    var builder = CreateSplitKeyOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(ObjectType.kmipTag)) {
      builder.objectType((ObjectType) map
          .get(ObjectType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(SplitKeyParts.kmipTag)) {
      builder.splitKeyParts((SplitKeyParts) map
          .get(SplitKeyParts.kmipTag)
          .getFirst());
    }
    if (map.containsKey(SplitKeyThreshold.kmipTag)) {
      builder.splitKeyThreshold((SplitKeyThreshold) map
          .get(SplitKeyThreshold.kmipTag)
          .getFirst());
    }
    if (map.containsKey(SplitKeyMethod.kmipTag)) {
      builder.splitKeyMethod((SplitKeyMethod) map
          .get(SplitKeyMethod.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Attributes.kmipTag)) {
      builder.attributes((Attributes) map
          .get(Attributes.kmipTag)
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
        .of(objectType, uniqueIdentifier, splitKeyParts, splitKeyThreshold, splitKeyMethod,
            attributes)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
