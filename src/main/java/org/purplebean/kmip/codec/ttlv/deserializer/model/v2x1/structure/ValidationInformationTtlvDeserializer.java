package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purplebean.kmip.model.core.enumeration.ValidationType;
import org.purplebean.kmip.model.v2x1.structure.ValidationInformation;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityCountry;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityUri;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateIdentifier;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;
import org.purplebean.kmip.model.v2x1.type.ValidationVendorUri;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMinor;

/**
 * TTLV deserializer for {@link ValidationInformation}.
 */
public class ValidationInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationInformation,
        ValidationInformation.ValidationInformationBuilder> {

  /**
   * Constructs a new {@link ValidationInformationTtlvDeserializer}.
   */
  public ValidationInformationTtlvDeserializer() {
    super(ValidationInformation.kmipTag, ValidationInformation.encodingType);
  }

  @Override
  protected ValidationInformation.ValidationInformationBuilder createBuilder() {
    return ValidationInformation.builder();
  }

  @Override
  protected void setValue(ValidationInformation.ValidationInformationBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VALIDATION_AUTHORITY_TYPE ->
          builder.validationAuthorityType(mapper.readValue(p, ValidationAuthorityType.class));
      case KmipTag.Standard.VALIDATION_AUTHORITY_COUNTRY ->
          builder.validationAuthorityCountry(mapper.readValue(p, ValidationAuthorityCountry.class));
      case KmipTag.Standard.VALIDATION_AUTHORITY_URI ->
          builder.validationAuthorityUri(mapper.readValue(p, ValidationAuthorityUri.class));
      case KmipTag.Standard.VALIDATION_VERSION_MAJOR ->
          builder.validationVersionMajor(mapper.readValue(p, ValidationVersionMajor.class));
      case KmipTag.Standard.VALIDATION_VERSION_MINOR ->
          builder.validationVersionMinor(mapper.readValue(p, ValidationVersionMinor.class));
      case KmipTag.Standard.VALIDATION_TYPE ->
          builder.validationType(mapper.readValue(p, ValidationType.class));
      case KmipTag.Standard.VALIDATION_LEVEL ->
          builder.validationLevel(mapper.readValue(p, ValidationLevel.class));
      case KmipTag.Standard.VALIDATION_CERTIFICATE_IDENTIFIER ->
          builder.validationCertificateIdentifier(
              mapper.readValue(p, ValidationCertificateIdentifier.class));
      case KmipTag.Standard.VALIDATION_CERTIFICATE_URI ->
          builder.validationCertificateUri(mapper.readValue(p, ValidationCertificateUri.class));
      case KmipTag.Standard.VALIDATION_VENDOR_URI ->
          builder.validationVendorUri(mapper.readValue(p, ValidationVendorUri.class));
      case KmipTag.Standard.VALIDATION_PROFILE ->
          builder.validationProfile(mapper.readValue(p, ValidationProfile.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ValidationInformation build(
      ValidationInformation.ValidationInformationBuilder builder) {
    return builder.build();
  }
}