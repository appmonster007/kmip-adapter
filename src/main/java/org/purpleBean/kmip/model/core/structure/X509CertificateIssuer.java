package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP X509CertificateIssuer attribute structure.
 *
 * <p>Represents a X509CertificateIssuer in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateIssuer implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.X_509_CERTIFICATE_ISSUER.inst();
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

    @Builder
    private X509CertificateIssuer(
            @NonNull IssuerDistinguishedName issuerDistinguishedName,
            List<IssuerAlternativeName> issuerAlternativeNames
    ) {
        this.issuerDistinguishedName = issuerDistinguishedName;
        this.issuerAlternativeNames = (issuerAlternativeNames == null) ? Collections.emptyList() : issuerAlternativeNames;
        validate();
    }

    public static X509CertificateIssuer of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure.getValue()).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return X509CertificateIssuer.builder()
                .issuerDistinguishedName((IssuerDistinguishedName) map.get(IssuerDistinguishedName.kmipTag).get(0))
                .issuerAlternativeNames(map.get(IssuerAlternativeName.kmipTag).stream().map(e -> (IssuerAlternativeName) e).collect(Collectors.toList()))
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(issuerDistinguishedName, "IssuerDistinguishedName cannot be null");
        Objects.requireNonNull(issuerAlternativeNames, "IssuerAlternativeNames cannot be null");
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
        return Stream.of(
                        issuerDistinguishedName,
                        issuerAlternativeNames)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
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
        return AttributeValueStructure.of(getValue());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }
}
