package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KMIP X509CertificateIdentifier attribute structure.
 *
 * <p>Represents a X509CertificateIdentifier in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateIdentifier implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.X_509_CERTIFICATE_IDENTIFIER.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, X509CertificateIdentifier.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, X509CertificateIdentifier.class, X509CertificateIdentifier::of);
        }
    }

    @NonNull
    private final IssuerDistinguishedName issuerDistinguishedName;

    @NonNull
    private final CertificateSerialNumber certificateSerialNumber;

    public X509CertificateIdentifier(@NonNull IssuerDistinguishedName issuerDistinguishedName, @NonNull CertificateSerialNumber certificateSerialNumber) {
        this.issuerDistinguishedName = Objects.requireNonNull(issuerDistinguishedName, "Issuer Distinguished Name cannot be null");
        this.certificateSerialNumber = Objects.requireNonNull(certificateSerialNumber, "Certificate Serial Number cannot be null");
    }

    public static X509CertificateIdentifier of(@NonNull ByteBuffer issuerDistinguishedName, @NonNull ByteBuffer certificateSerialNumber) {
        return new X509CertificateIdentifier(IssuerDistinguishedName.of(issuerDistinguishedName), CertificateSerialNumber.of(certificateSerialNumber));
    }

    public static X509CertificateIdentifier of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return X509CertificateIdentifier.builder()
                .issuerDistinguishedName((IssuerDistinguishedName) map.get(IssuerDistinguishedName.kmipTag).get(0))
                .certificateSerialNumber((CertificateSerialNumber) map.get(CertificateSerialNumber.kmipTag).get(0))
                .build();
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
    public List<KmipDataType> getValues() {
        return List.of(issuerDistinguishedName, certificateSerialNumber);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
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
        return getAttributeName().getValue();
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValueStructure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    public static class X509CertificateIdentifierBuilder {
        public X509CertificateIdentifier build() {
            validate();
            return new X509CertificateIdentifier(issuerDistinguishedName, certificateSerialNumber);
        }

        private void validate() {
            Objects.requireNonNull(issuerDistinguishedName, "Issuer Distinguished Name cannot be null");
            Objects.requireNonNull(certificateSerialNumber, "Certificate Serial Number cannot be null");

            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            if (!issuerDistinguishedName.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("Issuer Distinguished Name is not supported for KMIP spec %s", spec)
                );
            }
            if (!certificateSerialNumber.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("Certificate Serial Number is not supported for KMIP spec %s", spec)
                );
            }
        }
    }
}