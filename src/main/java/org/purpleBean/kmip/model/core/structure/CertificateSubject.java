package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP CertificateSubject attribute structure.
 *
 * <p>Represents a CertificateSubject in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CertificateSubject implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.CERTIFICATE_SUBJECT.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateSubject.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CertificateSubject.class, CertificateSubject::of);
        }
    }

    @NonNull
    private final CertificateSubjectDistinguishedName certificateSubjectDistinguishedName;
    @NonNull
    @Singular
    private final List<CertificateSubjectAlternativeName> certificateSubjectAlternativeNames;

    @Builder
    private CertificateSubject(
            @NonNull CertificateSubjectDistinguishedName certificateSubjectDistinguishedName,
            List<CertificateSubjectAlternativeName> certificateSubjectAlternativeNames
    ) {
        this.certificateSubjectDistinguishedName = certificateSubjectDistinguishedName;
        this.certificateSubjectAlternativeNames = (certificateSubjectAlternativeNames == null) ? Collections.emptyList() : certificateSubjectAlternativeNames;
        validate();
    }

    public static CertificateSubject of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure.getValue()).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return CertificateSubject.builder()
                .certificateSubjectDistinguishedName((CertificateSubjectDistinguishedName) map.get(CertificateSubjectDistinguishedName.kmipTag).get(0))
                .certificateSubjectAlternativeNames(map.get(CertificateSubjectAlternativeName.kmipTag).stream().map(e -> (CertificateSubjectAlternativeName) e).collect(Collectors.toList()))
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
        return Stream.of(certificateSubjectDistinguishedName, certificateSubjectAlternativeNames)
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
