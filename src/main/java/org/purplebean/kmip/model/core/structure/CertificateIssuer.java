package org.purplebean.kmip.model.core.structure;

import java.util.Collections;
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
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP CertificateIssuer attribute structure.
 *
 * <p>Represents a CertificateIssuer in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CertificateIssuer implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.CERTIFICATE_ISSUER.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateIssuer.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CertificateIssuer.class,
          CertificateIssuer::of);
    }
  }

  @NonNull
  private final CertificateIssuerDistinguishedName certificateIssuerDistinguishedName;
  @NonNull
  @Singular
  private final List<CertificateIssuerAlternativeName> certificateIssuerAlternativeNames;

  @Builder
  private CertificateIssuer(
      @NonNull CertificateIssuerDistinguishedName certificateIssuerDistinguishedName,
      List<CertificateIssuerAlternativeName> certificateIssuerAlternativeNames
  ) {
    this.certificateIssuerDistinguishedName = certificateIssuerDistinguishedName;
    this.certificateIssuerAlternativeNames =
        (certificateIssuerAlternativeNames == null) ? Collections.emptyList() :
            certificateIssuerAlternativeNames;
    validate();
  }

  /**
   * Returns the {@link CertificateIssuer} instance wrapping the given value.
   */
  public static CertificateIssuer of(@NonNull AttributeName attributeName,
                                     @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType
        || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure)
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return CertificateIssuer
        .builder()
        .certificateIssuerDistinguishedName((CertificateIssuerDistinguishedName) map
            .get(CertificateIssuerDistinguishedName.kmipTag)
            .get(0))
        .certificateIssuerAlternativeNames(map
            .get(CertificateIssuerAlternativeName.kmipTag)
            .stream()
            .map(e -> (CertificateIssuerAlternativeName) e)
            .collect(Collectors.toList()))
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // No validation needed for this structure
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
  public KmipDataType[] getValue() {
    return Stream
        .of(certificateIssuerDistinguishedName, certificateIssuerAlternativeNames)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public boolean isAlwaysPresent() {
    return true;
  }

  @Override
  public boolean isServerInitializable() {
    return true;
  }

  @Override
  public boolean isClientInitializable() {
    return false;
  }

  @Override
  public boolean isServerModifiable(State state) {
    return false;
  }

  @Override
  public boolean isClientModifiable(State state) {
    return false;
  }

  @Override
  public boolean isClientDeletable() {
    return false;
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return false;
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofStructure(getValue());
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }
}
