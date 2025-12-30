package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP X509CertificateIdentifier attribute structure.
 *
 * <p>Represents a X509CertificateIdentifier in KMIP.</p>
 */
@Data
@Builder
public class X509CertificateIdentifier implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.X_509_CERTIFICATE_IDENTIFIER);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supported_versions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supported_versions) {
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

    public static X509CertificateIdentifier of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        X509CertificateIdentifierBuilder builder = X509CertificateIdentifier.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof IssuerDistinguishedName issuerDistinguishedName) {
                builder.issuerDistinguishedName(issuerDistinguishedName);
            }
            if (field instanceof CertificateSerialNumber certificateSerialNumber) {
                builder.certificateSerialNumber(certificateSerialNumber);
            }
        }
        return builder.build();
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
        return Stream.of(issuerDistinguishedName, certificateSerialNumber).filter(Objects::nonNull).toList();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supported_versions.contains(spec)
                && issuerDistinguishedName.isSupported()
                && certificateSerialNumber.isSupported();
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
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.Structure.of(getValues());
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
