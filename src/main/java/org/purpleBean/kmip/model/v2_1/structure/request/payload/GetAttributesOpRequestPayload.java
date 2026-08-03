package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.type.AttributeReferenceTag;

@Data
@Builder(toBuilder = true)
public class GetAttributesOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.GET_ATTRIBUTES;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          GetAttributesOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, GetAttributesOpRequestPayload.class,
          GetAttributesOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @Singular("attributeReference")
  private final List<KmipDataType> attributeReferences;

  @Builder
  private GetAttributesOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      List<KmipDataType> attributeReferences
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.attributeReferences =
        (attributeReferences == null) ? Collections.emptyList() : attributeReferences;
    validate();
  }

  public static GetAttributesOpRequestPayload of(List<KmipDataType> values) {
    var builder = GetAttributesOpRequestPayload.builder();
    for (KmipDataType value : values) {
      if (value instanceof UniqueIdentifier u) {
        builder.uniqueIdentifier(u);
      } else if (value
          .getKmipTag()
          .getValue() == AttributeReferenceTag.kmipTag.getValue()) {
        builder.attributeReference(value);
      }
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
        .of(uniqueIdentifier, attributeReferences)
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
