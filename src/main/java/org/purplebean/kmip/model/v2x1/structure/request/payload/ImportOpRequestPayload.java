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
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.KeyWrapType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.type.ReplaceExisting;

/**
 * KMIP Import Request Payload.
 *
 * <p>Per KMIP v2.1/v3.0 spec §6.1.29:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 *   <li>ObjectType — Required</li>
 *   <li>ReplaceExisting — Optional</li>
 *   <li>KeyWrapType — Required if and only if the key object is wrapped</li>
 *   <li>Attributes — Required</li>
 *   <li>Any Object — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class ImportOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.IMPORT;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ImportOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, ImportOpRequestPayload.class,
          ImportOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final ObjectType objectType;

  private final ReplaceExisting replaceExisting;

  private final KeyWrapType keyWrapType;

  private final Attributes attributes;

  @NonNull
  private final ManagedObject object;

  @Builder
  private ImportOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      @NonNull ObjectType objectType,
      ReplaceExisting replaceExisting,
      KeyWrapType keyWrapType,
      Attributes attributes,
      @NonNull ManagedObject object
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.objectType = objectType;
    this.replaceExisting = replaceExisting;
    this.keyWrapType = keyWrapType;
    this.attributes = attributes;
    this.object = object;
    validate();
  }

  /**
   * Returns the {@link ImportOpRequestPayload} instance wrapping the given value.
   */
  public static ImportOpRequestPayload of(List<KmipDataType> values) {
    var builder = ImportOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ObjectType.kmipTag)) {
      builder.objectType((ObjectType) map
          .get(ObjectType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ReplaceExisting.kmipTag)) {
      builder.replaceExisting((ReplaceExisting) map
          .get(ReplaceExisting.kmipTag)
          .getFirst());
    }
    if (map.containsKey(KeyWrapType.kmipTag)) {
      builder.keyWrapType((KeyWrapType) map
          .get(KeyWrapType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Attributes.kmipTag)) {
      builder.attributes((Attributes) map
          .get(Attributes.kmipTag)
          .getFirst());
    }
    values
        .stream()
        .filter(v -> !v
            .getKmipTag()
            .equals(ObjectType.kmipTag)
            && !v
            .getKmipTag()
            .equals(UniqueIdentifier.kmipTag)
            && !v
            .getKmipTag()
            .equals(ReplaceExisting.kmipTag)
            && !v
            .getKmipTag()
            .equals(KeyWrapType.kmipTag)
            && !v
            .getKmipTag()
            .equals(Attributes.kmipTag))
        .filter(v -> v instanceof ManagedObject)
        .findFirst()
        .ifPresent(v -> builder.object((ManagedObject) v));
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
            objectType,
            replaceExisting,
            keyWrapType,
            attributes,
            object)
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
