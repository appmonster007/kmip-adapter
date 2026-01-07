package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.*;
import java.util.stream.Collectors;

/**
 * KMIP CertificateSubject attribute structure.
 *
 * <p>Represents a CertificateSubject in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CertificateSubject implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_SUBJECT);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1);

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

    public static CertificateSubject of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValue().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return CertificateSubject.builder()
                .certificateSubjectDistinguishedName((CertificateSubjectDistinguishedName) map.get(CertificateSubjectDistinguishedName.kmipTag).get(0))
                .certificateSubjectAlternativeNames(map.get(CertificateSubjectAlternativeName.kmipTag).stream().map(e -> (CertificateSubjectAlternativeName) e).collect(Collectors.toList()))
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
        ArrayList<KmipDataType> fields = new ArrayList<>();
        fields.add(certificateSubjectDistinguishedName);
        fields.addAll(certificateSubjectAlternativeNames);
        return fields;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && certificateSubjectDistinguishedName.isSupported()
                && certificateSubjectAlternativeNames.stream().allMatch(CertificateSubjectAlternativeName::isSupported);
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

    public static class CertificateSubjectBuilder {
        public CertificateSubject build() {
            validate();
            return new CertificateSubject(
                    certificateSubjectDistinguishedName,
                    certificateSubjectAlternativeNames
            );
        }

        private void validate() {
            Objects.requireNonNull(certificateSubjectDistinguishedName, "certificateSubjectDistinguishedName cannot be null");
            Objects.requireNonNull(certificateSubjectAlternativeNames, "certificateSubjectAlternativeNames cannot be null");
        }
    }
}