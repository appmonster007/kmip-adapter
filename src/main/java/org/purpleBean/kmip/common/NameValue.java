package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.Set;

/**
 * KMIP NameValue dataType.
 * Represents the value part of a Name attribute in KMIP.
 *
 * <p>According to KMIP v1.2, a NameValue is a text string that represents
 * the actual name value in a Name structure.</p>
 */
@Data
@Builder
public class NameValue implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.NAME_VALUE);
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        // Register with KmipDataType
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, NameValue.class);
        }
    }

    /**
     * The name value as a text string.
     */
    @NonNull
    private final String value;


    /**
     * Creates a new NameValue with the specified TextString value.
     *
     * @param value the name value as a TextString
     * @return a new NameValue instance
     */
    public static NameValue of(String value) {
        return new NameValue(value);
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

    /**
     * Gets the name value as a string.
     *
     * @return the name value as a string
     */
    public String getValue() {
        return value;
    }
}
