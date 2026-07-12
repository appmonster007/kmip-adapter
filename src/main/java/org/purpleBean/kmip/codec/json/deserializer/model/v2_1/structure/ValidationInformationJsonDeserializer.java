package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.ValidationInformation;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.type.*;

public class ValidationInformationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidationInformation, ValidationInformation.ValidationInformationBuilder> {

    public ValidationInformationJsonDeserializer() {
        super(ValidationInformation.kmipTag, ValidationInformation.encodingType);
    }

    @Override
    protected ValidationInformation.ValidationInformationBuilder createBuilder() {
        return ValidationInformation.builder();
    }

    @Override
    protected void setValue(ValidationInformation.ValidationInformationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.VALIDATION_AUTHORITY_TYPE -> builder.validationAuthorityType(ctxt.readValue(p, ValidationAuthorityType.class));
            case KmipTag.Standard.VALIDATION_AUTHORITY_COUNTRY -> builder.validationAuthorityCountry(ctxt.readValue(p, ValidationAuthorityCountry.class));
            case KmipTag.Standard.VALIDATION_AUTHORITY_URI -> builder.validationAuthorityUri(ctxt.readValue(p, ValidationAuthorityUri.class));
            case KmipTag.Standard.VALIDATION_VERSION_MAJOR -> builder.validationVersionMajor(ctxt.readValue(p, ValidationVersionMajor.class));
            case KmipTag.Standard.VALIDATION_VERSION_MINOR -> builder.validationVersionMinor(ctxt.readValue(p, ValidationVersionMinor.class));
            case KmipTag.Standard.VALIDATION_TYPE -> builder.validationType(ctxt.readValue(p, ValidationType.class));
            case KmipTag.Standard.VALIDATION_LEVEL -> builder.validationLevel(ctxt.readValue(p, ValidationLevel.class));
            case KmipTag.Standard.VALIDATION_CERTIFICATE_IDENTIFIER -> builder.validationCertificateIdentifier(ctxt.readValue(p, ValidationCertificateIdentifier.class));
            case KmipTag.Standard.VALIDATION_CERTIFICATE_URI -> builder.validationCertificateUri(ctxt.readValue(p, ValidationCertificateUri.class));
            case KmipTag.Standard.VALIDATION_VENDOR_URI -> builder.validationVendorUri(ctxt.readValue(p, ValidationVendorUri.class));
            case KmipTag.Standard.VALIDATION_PROFILE -> builder.validationProfile(ctxt.readValue(p, ValidationProfile.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ValidationInformation build(ValidationInformation.ValidationInformationBuilder builder) {
        return builder.build();
    }
}