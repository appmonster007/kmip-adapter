package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP X509CertificateSubject attribute structure.
 *
 * <p>Represents a X509CertificateSubject in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class X509CertificateSubject implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.X_509_CERTIFICATE_SUBJECT);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
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

    public static X509CertificateSubject of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        X509CertificateSubjectBuilder builder = X509CertificateSubject.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof SubjectDistinguishedName subjectDistinguishedName) {
                builder.subjectDistinguishedName(subjectDistinguishedName);
            } else if (field instanceof SubjectAlternativeName subjectAlternativeName) {
                builder.subjectAlternativeName(subjectAlternativeName);
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
        fields.add(subjectDistinguishedName);
        fields.addAll(subjectAlternativeNames);
        return fields;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && subjectDistinguishedName.isSupported()
                && subjectAlternativeNames.stream().allMatch(KmipDataType::isSupported);
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