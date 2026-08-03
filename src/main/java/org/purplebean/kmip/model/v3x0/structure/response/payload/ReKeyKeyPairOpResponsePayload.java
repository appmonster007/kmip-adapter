package org.purplebean.kmip.model.v3x0.structure.response.payload;

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
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.v3x0.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.v3x0.type.PublicKeyUniqueIdentifier;

/**
 * KMIP ReKeyKeyPair Response Payload (V3_0).
 * <p>
 * Fork of
 * {@link org.purplebean.kmip.model.v1x2.structure.response.payload.ReKeyKeyPairOpResponsePayload} for
 * KMIP 3.0, where {@code PrivateKeyUniqueIdentifier}/{@code PublicKeyUniqueIdentifier} are
 * encoded as
 * {@code Identifier} rather than {@code TextString} (see {@link PrivateKeyUniqueIdentifier},
 * {@link PublicKeyUniqueIdentifier}).
 */
@Data
@Builder(toBuilder = true)
public class ReKeyKeyPairOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.RE_KEY_KEY_PAIR;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          ReKeyKeyPairOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, ReKeyKeyPairOpResponsePayload.class,
          ReKeyKeyPairOpResponsePayload::of);
    }
  }

  @NonNull
  private final PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier;
  @NonNull
  private final PublicKeyUniqueIdentifier publicKeyUniqueIdentifier;
  private final PrivateKeyTemplateAttribute privateKeyTemplateAttribute;
  private final PublicKeyTemplateAttribute publicKeyTemplateAttribute;

  @Builder
  private ReKeyKeyPairOpResponsePayload(
      @NonNull PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
      @NonNull PublicKeyUniqueIdentifier publicKeyUniqueIdentifier,
      PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
      PublicKeyTemplateAttribute publicKeyTemplateAttribute
  ) {
    this.privateKeyUniqueIdentifier = privateKeyUniqueIdentifier;
    this.publicKeyUniqueIdentifier = publicKeyUniqueIdentifier;
    this.privateKeyTemplateAttribute = privateKeyTemplateAttribute;
    this.publicKeyTemplateAttribute = publicKeyTemplateAttribute;
    validate();
  }

  /**
   * Returns the {@link ReKeyKeyPairOpResponsePayload} instance wrapping the given value.
   */
  public static ReKeyKeyPairOpResponsePayload of(
      @NonNull PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
      @NonNull PublicKeyUniqueIdentifier publicKeyUniqueIdentifier,
      PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
      PublicKeyTemplateAttribute publicKeyTemplateAttribute
  ) {
    return ReKeyKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(privateKeyUniqueIdentifier)
        .publicKeyUniqueIdentifier(publicKeyUniqueIdentifier)
        .privateKeyTemplateAttribute(privateKeyTemplateAttribute)
        .publicKeyTemplateAttribute(publicKeyTemplateAttribute)
        .build();
  }

  /**
   * Returns the {@link ReKeyKeyPairOpResponsePayload} instance wrapping the given value.
   */
  public static ReKeyKeyPairOpResponsePayload of(List<KmipDataType> values) {
    var builder = ReKeyKeyPairOpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(PrivateKeyUniqueIdentifier.kmipTag)) {
      builder.privateKeyUniqueIdentifier((PrivateKeyUniqueIdentifier) map
          .get(PrivateKeyUniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PublicKeyUniqueIdentifier.kmipTag)) {
      builder.publicKeyUniqueIdentifier((PublicKeyUniqueIdentifier) map
          .get(PublicKeyUniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PrivateKeyTemplateAttribute.kmipTag)) {
      builder.privateKeyTemplateAttribute((PrivateKeyTemplateAttribute) map
          .get(PrivateKeyTemplateAttribute.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PublicKeyTemplateAttribute.kmipTag)) {
      builder.publicKeyTemplateAttribute((PublicKeyTemplateAttribute) map
          .get(KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE)
          .getFirst());
    }
    return builder.build();
  }

  private void validate() {
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
            privateKeyUniqueIdentifier,
            publicKeyUniqueIdentifier,
            privateKeyTemplateAttribute,
            publicKeyTemplateAttribute)
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
