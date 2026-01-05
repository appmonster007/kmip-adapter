package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP SubjectDistinguishedName dataType.
 */
@Data
@Builder(toBuilder = true)
public class SubjectDistinguishedName implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME);
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SubjectDistinguishedName.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static SubjectDistinguishedName of(@NonNull ByteBuffer value) {
        return SubjectDistinguishedName.builder().value(value).build();
    }

    public static SubjectDistinguishedName of(byte[] value) {
        return SubjectDistinguishedName.builder().value(ByteBuffer.wrap(value)).build();
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