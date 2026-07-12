package org.purpleBean.kmip.model.v3_0.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.util.StringUtils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP NistSecurityCategory datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class NistSecurityCategory implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = KmipTag.Standard.NIST_SECURITY_CATEGORY.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, NistSecurityCategory.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, NistSecurityCategory.class, NistSecurityCategory::of);
        }
    }

    @NonNull
    private final Integer value;

    @Builder
    private NistSecurityCategory(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    public static NistSecurityCategory of(@NonNull Integer value) {
        return new NistSecurityCategory(value);
    }

    public static NistSecurityCategory of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof Integer value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new NistSecurityCategory(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofInteger(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return kmipTag.getDescription();
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
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }

    @Override
    public boolean isAlwaysPresent() {
        return false; // TODO: Adjust as needed
    }

    @Override
    public boolean isServerInitializable() {
        return true; // TODO: Adjust as needed
    }

    @Override
    public boolean isClientInitializable() {
        return false;
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        return false;
    }

    @Override
    public boolean isClientModifiable(@NonNull State state) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NistSecurityCategory that = (NistSecurityCategory) o;
        // TODO: Adjust equals for specific data type if needed (e.g., OffsetDateTime.withNano(0).atZoneSameInstant(ZoneOffset.UTC))
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        // TODO: Adjust hashCode for specific data type if needed (e.g., OffsetDateTime.withNano(0).atZoneSameInstant(ZoneOffset.UTC))
        return Objects.hash(value);
    }
}