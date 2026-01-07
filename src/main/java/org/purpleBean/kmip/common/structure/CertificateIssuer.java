package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

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

    public static CertificateIssuer of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        CertificateIssuerBuilder builder = CertificateIssuer.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof CertificateIssuerDistinguishedName certificateIssuerDistinguishedName) {
                builder.certificateIssuerDistinguishedName(certificateIssuerDistinguishedName);
            } else if (field instanceof CertificateIssuerAlternativeName certificateIssuerAlternativeName) {
                builder.certificateIssuerAlternativeName(certificateIssuerAlternativeName);
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
        fields.add(certificateIssuerDistinguishedName);
        fields.addAll(certificateIssuerAlternativeNames);
        return fields;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && certificateIssuerDistinguishedName.isSupported()
                && certificateIssuerAlternativeNames.stream().allMatch(KmipDataType::isSupported);
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