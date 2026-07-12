package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.ValidationInformation;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.type.*;

public class ValidationInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationInformation, ValidationInformation.ValidationInformationBuilder> {

    public ValidationInformationTtlvDeserializer() {
        super(ValidationInformation.kmipTag, ValidationInformation.encodingType);
    }

    @Override
    protected ValidationInformation.ValidationInformationBuilder createBuilder() {
        return ValidationInformation.builder();
    }

    @Override
    protected void setValue(ValidationInformation.ValidationInformationBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.VALIDATION_AUTHORITY_TYPE -> builder.validationAuthorityType(mapper.readValue(p, ValidationAuthorityType.class));
            case KmipTag.Standard.VALIDATION_AUTHORITY_COUNTRY -> builder.validationAuthorityCountry(mapper.readValue(p, ValidationAuthorityCountry.class));
            case KmipTag.Standard.VALIDATION_AUTHORITY_URI -> builder.validationAuthorityUri(mapper.readValue(p, ValidationAuthorityUri.class));
            case KmipTag.Standard.VALIDATION_VERSION_MAJOR -> builder.validationVersionMajor(mapper.readValue(p, ValidationVersionMajor.class));
            case KmipTag.Standard.VALIDATION_VERSION_MINOR -> builder.validationVersionMinor(mapper.readValue(p, ValidationVersionMinor.class));
            case KmipTag.Standard.VALIDATION_TYPE -> builder.validationType(mapper.readValue(p, ValidationType.class));
            case KmipTag.Standard.VALIDATION_LEVEL -> builder.validationLevel(mapper.readValue(p, ValidationLevel.class));
            case KmipTag.Standard.VALIDATION_CERTIFICATE_IDENTIFIER -> builder.validationCertificateIdentifier(mapper.readValue(p, ValidationCertificateIdentifier.class));
            case KmipTag.Standard.VALIDATION_CERTIFICATE_URI -> builder.validationCertificateUri(mapper.readValue(p, ValidationCertificateUri.class));
            case KmipTag.Standard.VALIDATION_VENDOR_URI -> builder.validationVendorUri(mapper.readValue(p, ValidationVendorUri.class));
            case KmipTag.Standard.VALIDATION_PROFILE -> builder.validationProfile(mapper.readValue(p, ValidationProfile.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ValidationInformation build(ValidationInformation.ValidationInformationBuilder builder) {
        return builder.build();
    }
}