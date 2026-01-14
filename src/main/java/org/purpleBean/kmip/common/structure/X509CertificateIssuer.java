package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.*;
import java.util.stream.Collectors;

/**
 * KMIP X509CertificateIssuer attribute structure.
 *
 * <p>Represents a X509CertificateIssuer in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateIssuer implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.X_509_CERTIFICATE_ISSUER);
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

    public static X509CertificateIssuer of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return X509CertificateIssuer.builder()
                .issuerDistinguishedName((IssuerDistinguishedName) map.get(IssuerDistinguishedName.kmipTag).get(0))
                .issuerAlternativeNames(map.get(IssuerAlternativeName.kmipTag).stream().map(e -> (IssuerAlternativeName) e).collect(Collectors.toList()))
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
        List<KmipDataType> fields = new ArrayList<>();
        fields.add(issuerDistinguishedName);
        fields.addAll(issuerAlternativeNames);
        return fields.stream().filter(Objects::nonNull).collect(Collectors.toList());
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