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
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

@Data
@Builder(toBuilder = true)
public class JoinSplitKeyOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.JOIN_SPLIT_KEY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          JoinSplitKeyOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, JoinSplitKeyOpRequestPayload.class,
          JoinSplitKeyOpRequestPayload::of);
    }
  }

  @NonNull
  private final ObjectType objectType;

  @NonNull
  @Singular
  private final List<UniqueIdentifier> uniqueIdentifiers;

  private final SecretDataType secretDataType;

  private final TemplateAttribute templateAttribute;

  @Builder
  private JoinSplitKeyOpRequestPayload(
      @NonNull ObjectType objectType,
      @NonNull List<UniqueIdentifier> uniqueIdentifiers,
      SecretDataType secretDataType,
      TemplateAttribute templateAttribute
  ) {
    this.objectType = objectType;
    this.uniqueIdentifiers = uniqueIdentifiers;
    this.secretDataType = secretDataType;
    this.templateAttribute = templateAttribute;
    validate();
  }

  public static JoinSplitKeyOpRequestPayload of(List<KmipDataType> values) {
    var builder = JoinSplitKeyOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(ObjectType.kmipTag)) {
      builder.objectType((ObjectType) map
          .get(ObjectType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      map
          .get(UniqueIdentifier.kmipTag)
          .forEach(item -> builder.uniqueIdentifier((UniqueIdentifier) item));
    }
    if (map.containsKey(SecretDataType.kmipTag)) {
      builder.secretDataType((SecretDataType) map
          .get(SecretDataType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(TemplateAttribute.kmipTag)) {
      builder.templateAttribute((TemplateAttribute) map
          .get(TemplateAttribute.kmipTag)
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
            objectType,
            uniqueIdentifiers,
            secretDataType,
            templateAttribute)
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
