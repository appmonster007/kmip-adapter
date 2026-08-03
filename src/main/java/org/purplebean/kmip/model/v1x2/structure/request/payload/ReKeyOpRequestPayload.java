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
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP ReKeyOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class ReKeyOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.RE_KEY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReKeyOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, ReKeyOpRequestPayload.class,
          ReKeyOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  private final Offset offset;
  private final TemplateAttribute templateAttribute;

  @Builder
  private ReKeyOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      Offset offset,
      TemplateAttribute templateAttribute
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.offset = offset;
    this.templateAttribute = templateAttribute;
    validate();
  }

  /**
   * Returns the {@link ReKeyOpRequestPayload} instance wrapping the given value.
   */
  public static ReKeyOpRequestPayload of(
      UniqueIdentifier uniqueIdentifier,
      Offset offset,
      TemplateAttribute templateAttribute
  ) {
    return ReKeyOpRequestPayload
        .builder()
        .uniqueIdentifier(uniqueIdentifier)
        .offset(offset)
        .templateAttribute(templateAttribute)
        .build();
  }

  /**
   * Returns the {@link ReKeyOpRequestPayload} instance wrapping the given value.
   */
  public static ReKeyOpRequestPayload of(List<KmipDataType> values) {
    var builder = ReKeyOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Offset.kmipTag)) {
      builder.offset((Offset) map
          .get(Offset.kmipTag)
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
    // Add validation logic here
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
            offset,
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
