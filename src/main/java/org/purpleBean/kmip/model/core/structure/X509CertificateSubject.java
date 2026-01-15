package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.util.*;
import java.util.stream.Collectors;

/**
 * KMIP X509CertificateSubject attribute structure.
 *
 * <p>Represents a X509CertificateSubject in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateSubject implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.X_509_CERTIFICATE_SUBJECT.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

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

    public static X509CertificateSubject of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return X509CertificateSubject.builder()
                .subjectDistinguishedName((SubjectDistinguishedName) map.get(SubjectDistinguishedName.kmipTag).get(0))
                .subjectAlternativeNames(map.get(SubjectAlternativeName.kmipTag).stream().map(e -> (SubjectAlternativeName) e).collect(Collectors.toList()))
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
        fields.add(subjectDistinguishedName);
        fields.addAll(subjectAlternativeNames);
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

    public static class X509CertificateSubjectBuilder {
        public X509CertificateSubject build() {
            validate();
            return new X509CertificateSubject(
                    subjectDistinguishedName,
                    subjectAlternativeNames
            );
        }

        private void validate() {
            Objects.requireNonNull(subjectDistinguishedName, "SubjectDistinguishedName cannot be null");
            Objects.requireNonNull(subjectAlternativeNames, "SubjectAlternativeNames cannot be null");
        }
    }
}