package org.purpleBean.kmip.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.RotateAutomatic;

/**
 * KMIP RotateAutomatic datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class RotateAutomatic implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = KmipTag.Standard.ROTATE_AUTOMATIC.inst();
    public static final EncodingType encodingType = EncodingType.BOOLEAN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RotateAutomatic.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, RotateAutomatic.class, RotateAutomatic::of);
        }
    }

    @NonNull
    private final Boolean value;

    @Builder
    private RotateAutomatic(@NonNull Boolean value) {
        this.value = value;
        validate();
    }

    public static RotateAutomatic of(@NonNull Boolean value) {
        return new RotateAutomatic(value);
    }

    public static RotateAutomatic of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof Boolean value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new RotateAutomatic(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofBoolean(value);
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
        return false;
    }

    @Override
    public boolean isServerInitializable() {
        return true;
    }

    @Override
    public boolean isClientInitializable() {
        return true;
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        return true;
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
        RotateAutomatic that = (RotateAutomatic) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}