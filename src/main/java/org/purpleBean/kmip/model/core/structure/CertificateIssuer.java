package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP CertificateIssuer attribute structure.
 *
 * <p>Represents a CertificateIssuer in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CertificateIssuer implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.CERTIFICATE_ISSUER.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateIssuer.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CertificateIssuer.class, CertificateIssuer::of);
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
        this.certificateIssuerAlternativeNames = (certificateIssuerAlternativeNames == null) ? Collections.emptyList() : certificateIssuerAlternativeNames;
        validate();
    }

    public static CertificateIssuer of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure.getValue()).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return CertificateIssuer.builder()
                .certificateIssuerDistinguishedName((CertificateIssuerDistinguishedName) map.get(CertificateIssuerDistinguishedName.kmipTag).get(0))
                .certificateIssuerAlternativeNames(map.get(CertificateIssuerAlternativeName.kmipTag).stream().map(e -> (CertificateIssuerAlternativeName) e).collect(Collectors.toList()))
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
        return Stream.of(certificateIssuerDistinguishedName, certificateIssuerAlternativeNames)
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
