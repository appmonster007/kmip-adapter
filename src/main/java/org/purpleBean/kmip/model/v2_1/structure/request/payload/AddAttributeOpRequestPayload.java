package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;

@Data
@Builder(toBuilder = true)
public class AddAttributeOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.ADD_ATTRIBUTE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          AddAttributeOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, AddAttributeOpRequestPayload.class,
          AddAttributeOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  @NonNull
  private final NewAttribute newAttribute;

  @Builder
  private AddAttributeOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      @NonNull NewAttribute newAttribute
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.newAttribute = newAttribute;
    validate();
  }

  public static AddAttributeOpRequestPayload of(List<KmipDataType> values) {
    var builder = AddAttributeOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      } else if (value instanceof NewAttribute) {
        builder.newAttribute((NewAttribute) value);
      }
    });
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
        .of(uniqueIdentifier, newAttribute)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
