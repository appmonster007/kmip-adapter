package org.purpleBean.kmip.model.v1_2.structure.request.payload;

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
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

@Data
@Builder(toBuilder = true)
public class PutOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.PUT;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PutOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, PutOpRequestPayload.class,
          PutOpRequestPayload::of);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final PutFunction putFunction;

  private final ReplacedUniqueIdentifier replacedUniqueIdentifier;

  @NonNull
  private final ManagedObject object;

  @Singular
  private final List<Attribute> attributes;

  @Builder
  private PutOpRequestPayload(
      @NonNull UniqueIdentifier uniqueIdentifier,
      @NonNull PutFunction putFunction,
      ReplacedUniqueIdentifier replacedUniqueIdentifier,
      @NonNull ManagedObject object,
      List<Attribute> attributes
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.putFunction = putFunction;
    this.replacedUniqueIdentifier = replacedUniqueIdentifier;
    this.object = object;
    this.attributes = attributes;
    validate();
  }

  public static PutOpRequestPayload of(List<KmipDataType> values) {
    var builder = PutOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PutFunction.kmipTag)) {
      builder.putFunction((PutFunction) map
          .get(PutFunction.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ReplacedUniqueIdentifier.kmipTag)) {
      builder.replacedUniqueIdentifier((ReplacedUniqueIdentifier) map
          .get(ReplacedUniqueIdentifier.kmipTag)
          .getFirst());
    }

    values
        .stream()
        .filter(v -> !v
            .getKmipTag()
            .equals(ObjectType.kmipTag) && !v
            .getKmipTag()
            .equals(TemplateAttribute.kmipTag))
        .filter(v -> v instanceof ManagedObject)
        .findFirst()
        .ifPresent(v -> builder.object((ManagedObject) v));

    if (map.containsKey(Attribute.kmipTag)) {
      map
          .get(Attribute.kmipTag)
          .forEach(item -> builder.attribute((Attribute) item));
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
            putFunction,
            replacedUniqueIdentifier,
            object,
            attributes)
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
