package org.purplebean.kmip.model.core.structure;

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
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * KMIP Certificate attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Certificate implements ManagedObject, KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.CERTIFICATE.inst();
  public static final ObjectType.Value objectTypeValue = ObjectType.Standard.CERTIFICATE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Certificate.class);
      ManagedObject.register(spec, kmipTag.getValue(), encodingType, objectTypeValue,
          Certificate.class, Certificate::of);
    }
  }

  @NonNull
  private final CertificateType certificateType;

  @NonNull
  private final CertificateValue certificateValue;

  @Builder
  private Certificate(
      @NonNull CertificateType certificateType,
      @NonNull CertificateValue certificateValue
  ) {
    this.certificateType = certificateType;
    this.certificateValue = certificateValue;
    validate();
  }

  /**
   * Returns the {@link Certificate} instance wrapping the given value.
   */
  public static Certificate of(
      @NonNull CertificateType certificateType,
      @NonNull CertificateValue certificateValue
  ) {
    return Certificate
        .builder()
        .certificateType(certificateType)
        .certificateValue(certificateValue)
        .build();
  }

  /**
   * Returns the {@link Certificate} instance wrapping the given value.
   */
  public static Certificate of(List<KmipDataType> values) {
    var builder = Certificate.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(CertificateType.kmipTag)) {
      builder.certificateType((CertificateType) map
          .get(CertificateType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CertificateValue.kmipTag)) {
      builder.certificateValue((CertificateValue) map
          .get(CertificateValue.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(certificateType, "CertificateType cannot be null");
    Objects.requireNonNull(certificateValue, "CertificateValue cannot be null");
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
        .of(certificateType, certificateValue)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public ObjectType getObjectType() {
    return objectTypeValue.inst();
  }
}
