package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.*;
import java.util.stream.Collectors;

/**
 * KMIP CertificateIssuer attribute structure.
 *
 * <p>Represents a CertificateIssuer in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CertificateIssuer implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_ISSUER);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
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

    public static CertificateIssuer of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return CertificateIssuer.builder()
                .certificateIssuerDistinguishedName((CertificateIssuerDistinguishedName) map.get(CertificateIssuerDistinguishedName.kmipTag).get(0))
                .certificateIssuerAlternativeNames(map.get(CertificateIssuerAlternativeName.kmipTag).stream().map(e -> (CertificateIssuerAlternativeName) e).collect(Collectors.toList()))
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
        fields.add(certificateIssuerDistinguishedName);
        fields.addAll(certificateIssuerAlternativeNames);
        return fields;
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

    public static class CertificateIssuerBuilder {
        public CertificateIssuer build() {
            validate();
            return new CertificateIssuer(
                    certificateIssuerDistinguishedName,
                    certificateIssuerAlternativeNames
            );
        }

        private void validate() {
            Objects.requireNonNull(certificateIssuerDistinguishedName, "CertificateIssuerDistinguishedName cannot be null");
            Objects.requireNonNull(certificateIssuerAlternativeNames, "CertificateIssuerAlternativeNames cannot be null");
        }
    }
}