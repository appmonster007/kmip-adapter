package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP X509CertificateSubject attribute structure.
 *
 * <p>Represents a X509CertificateSubject in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateSubject implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.X_509_CERTIFICATE_SUBJECT.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, X509CertificateSubject.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, X509CertificateSubject.class, X509CertificateSubject::of);
        }
    }

    @NonNull
    private final SubjectDistinguishedName subjectDistinguishedName;
    @NonNull
    @Singular
    private final List<SubjectAlternativeName> subjectAlternativeNames;

    @Builder
    private X509CertificateSubject(
            @NonNull SubjectDistinguishedName subjectDistinguishedName,
            List<SubjectAlternativeName> subjectAlternativeNames
    ) {
        this.subjectDistinguishedName = subjectDistinguishedName;
        this.subjectAlternativeNames = (subjectAlternativeNames == null) ? Collections.emptyList() : subjectAlternativeNames;
        validate();
    }

    public static X509CertificateSubject of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return X509CertificateSubject.builder()
                .subjectDistinguishedName((SubjectDistinguishedName) map.get(SubjectDistinguishedName.kmipTag).get(0))
                .subjectAlternativeNames(map.get(SubjectAlternativeName.kmipTag).stream().map(e -> (SubjectAlternativeName) e).collect(Collectors.toList()))
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(subjectDistinguishedName, "SubjectDistinguishedName cannot be null");
        Objects.requireNonNull(subjectAlternativeNames, "SubjectAlternativeNames cannot be null");
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
        return Stream.of(subjectDistinguishedName, subjectAlternativeNames).filter(Objects::nonNull)
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
        return kmipTag.getDescription();
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofStructure(getValue());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
    }
}