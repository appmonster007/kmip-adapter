package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP X509CertificateIssuer attribute structure.
 *
 * <p>Represents a X509CertificateIssuer in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateIssuer implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.X_509_CERTIFICATE_ISSUER);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, X509CertificateIssuer.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, X509CertificateIssuer.class, X509CertificateIssuer::of);
        }
    }

    @NonNull
    private final IssuerDistinguishedName issuerDistinguishedName;
    @NonNull
    @Singular
    private final List<IssuerAlternativeName> issuerAlternativeNames;

    public static X509CertificateIssuer of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        X509CertificateIssuerBuilder builder = X509CertificateIssuer.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof IssuerDistinguishedName issuerDistinguishedName) {
                builder.issuerDistinguishedName(issuerDistinguishedName);
            } else if (field instanceof IssuerAlternativeName issuerAlternativeName) {
                builder.issuerAlternativeName(issuerAlternativeName);
            } else {
                throw new IllegalArgumentException("Unsupported field type: " + field.getClass());
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
        List<KmipDataType> fields = new ArrayList<>();
        fields.add(issuerDistinguishedName);
        fields.addAll(issuerAlternativeNames);
        return fields;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && issuerDistinguishedName.isSupported()
                && issuerAlternativeNames.stream().allMatch(KmipDataType::isSupported);
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

    public static class X509CertificateIssuerBuilder {
        public X509CertificateIssuer build() {
            validate();
            return new X509CertificateIssuer(
                    issuerDistinguishedName,
                    issuerAlternativeNames
            );
        }

        private void validate() {
            Objects.requireNonNull(issuerDistinguishedName, "IssuerDistinguishedName cannot be null");
            Objects.requireNonNull(issuerAlternativeNames, "IssuerAlternativeNames cannot be null");
        }
    }
}