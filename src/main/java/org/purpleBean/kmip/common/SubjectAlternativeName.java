package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP SubjectAlternativeName dataType.
 */
@Data
@Builder
public class SubjectAlternativeName implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME);
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SubjectAlternativeName.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static SubjectAlternativeName of(@NonNull ByteBuffer value) {
        return SubjectAlternativeName.builder().value(value).build();
    }

    public static SubjectAlternativeName of(byte[] value) {
        return SubjectAlternativeName.builder().value(ByteBuffer.wrap(value)).build();
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
}