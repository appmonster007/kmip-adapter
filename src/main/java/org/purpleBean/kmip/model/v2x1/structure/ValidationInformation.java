package org.purplebean.kmip.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purplebean.kmip.model.core.enumeration.ValidationType;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityCountry;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityUri;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateIdentifier;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;
import org.purplebean.kmip.model.v2x1.type.ValidationVendorUri;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMinor;

@Data
@Builder(toBuilder = true)
public class ValidationInformation implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.VALIDATION_INFORMATION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ValidationInformation.class);
    }
  }

  @NonNull
  private final ValidationAuthorityType validationAuthorityType;
  private final ValidationAuthorityCountry validationAuthorityCountry;
  private final ValidationAuthorityUri validationAuthorityUri;
  @NonNull
  private final ValidationVersionMajor validationVersionMajor;
  private final ValidationVersionMinor validationVersionMinor;
  @NonNull
  private final ValidationType validationType;
  @NonNull
  private final ValidationLevel validationLevel;
  private final ValidationCertificateIdentifier validationCertificateIdentifier;
  private final ValidationCertificateUri validationCertificateUri;
  private final ValidationVendorUri validationVendorUri;
  @NonNull
  @Singular("validationProfile")
  private final List<ValidationProfile> validationProfiles;

  @Builder
  private ValidationInformation(@NonNull ValidationAuthorityType validationAuthorityType,
                                ValidationAuthorityCountry validationAuthorityCountry,
                                ValidationAuthorityUri validationAuthorityUri,
                                @NonNull ValidationVersionMajor validationVersionMajor,
                                ValidationVersionMinor validationVersionMinor,
                                @NonNull ValidationType validationType,
                                @NonNull ValidationLevel validationLevel,
                                ValidationCertificateIdentifier validationCertificateIdentifier,
                                ValidationCertificateUri validationCertificateUri,
                                ValidationVendorUri validationVendorUri,
                                List<ValidationProfile> validationProfiles) {
    this.validationAuthorityType = validationAuthorityType;
    this.validationAuthorityCountry = validationAuthorityCountry;
    this.validationAuthorityUri = validationAuthorityUri;
    this.validationVersionMajor = validationVersionMajor;
    this.validationVersionMinor = validationVersionMinor;
    this.validationType = validationType;
    this.validationLevel = validationLevel;
    this.validationCertificateIdentifier = validationCertificateIdentifier;
    this.validationCertificateUri = validationCertificateUri;
    this.validationVendorUri = validationVendorUri;
    this.validationProfiles =
        (validationProfiles == null) ? Collections.emptyList() : validationProfiles;
    validate();
  }

  public static ValidationInformation of(@NonNull ValidationAuthorityType authorityType,
                                         @NonNull ValidationVersionMajor versionMajor,
                                         @NonNull ValidationType type,
                                         @NonNull ValidationLevel level) {
    return ValidationInformation
        .builder()
        .validationAuthorityType(authorityType)
        .validationVersionMajor(versionMajor)
        .validationType(type)
        .validationLevel(level)
        .build();
  }

  public static ValidationInformation of(@NonNull KmipDataType value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
    var builder = ValidationInformation.builder();
    for (KmipDataType field : structure.getValue()) {
      if (field instanceof ValidationAuthorityType t) {
        builder.validationAuthorityType(t);
      } else if (field instanceof ValidationAuthorityCountry c) {
        builder.validationAuthorityCountry(c);
      } else if (field instanceof ValidationAuthorityUri u) {
        builder.validationAuthorityUri(u);
      } else if (field instanceof ValidationVersionMajor m) {
        builder.validationVersionMajor(m);
      } else if (field instanceof ValidationVersionMinor m) {
        builder.validationVersionMinor(m);
      } else if (field instanceof ValidationType t) {
        builder.validationType(t);
      } else if (field instanceof ValidationLevel l) {
        builder.validationLevel(l);
      } else if (field instanceof ValidationCertificateIdentifier i) {
        builder.validationCertificateIdentifier(i);
      } else if (field instanceof ValidationCertificateUri u) {
        builder.validationCertificateUri(u);
      } else if (field instanceof ValidationVendorUri u) {
        builder.validationVendorUri(u);
      } else if (field instanceof ValidationProfile p) {
        builder.validationProfile(p);
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
        .concat(
            Stream.of(validationAuthorityType, validationAuthorityCountry, validationAuthorityUri,
                validationVersionMajor, validationVersionMinor, validationType, validationLevel,
                validationCertificateIdentifier, validationCertificateUri, validationVendorUri),
            validationProfiles.stream())
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
