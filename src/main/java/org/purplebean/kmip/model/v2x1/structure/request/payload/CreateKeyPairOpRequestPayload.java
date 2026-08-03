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
import org.purplebean.kmip.model.v2x1.structure.CommonAttributes;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;

/**
 * KMIP CreateKeyPairOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class CreateKeyPairOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CREATE_KEY_PAIR;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CreateKeyPairOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, CreateKeyPairOpRequestPayload.class,
          CreateKeyPairOpRequestPayload::of);
    }
  }

  private final CommonAttributes commonAttributes;
  private final PrivateKeyAttributes privateKeyAttributes;
  private final PublicKeyAttributes publicKeyAttributes;

  @Builder
  private CreateKeyPairOpRequestPayload(
      CommonAttributes commonAttributes,
      PrivateKeyAttributes privateKeyAttributes,
      PublicKeyAttributes publicKeyAttributes
  ) {
    this.commonAttributes = commonAttributes;
    this.privateKeyAttributes = privateKeyAttributes;
    this.publicKeyAttributes = publicKeyAttributes;
    validate();
  }

  /**
   * Returns the {@link CreateKeyPairOpRequestPayload} instance wrapping the given value.
   */
  public static CreateKeyPairOpRequestPayload of(List<KmipDataType> values) {
    var builder = CreateKeyPairOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(CommonAttributes.kmipTag)) {
      builder.commonAttributes((CommonAttributes) map
          .get(CommonAttributes.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PrivateKeyAttributes.kmipTag)) {
      builder.privateKeyAttributes((PrivateKeyAttributes) map
          .get(PrivateKeyAttributes.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PublicKeyAttributes.kmipTag)) {
      builder.publicKeyAttributes((PublicKeyAttributes) map
          .get(PublicKeyAttributes.kmipTag)
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
        .of(commonAttributes, privateKeyAttributes, publicKeyAttributes)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
