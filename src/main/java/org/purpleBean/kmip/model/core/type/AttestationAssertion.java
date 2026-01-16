package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP AttestationAssertion dataType.
 */
@Data
@Builder(toBuilder = true)
public class AttestationAssertion implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.ATTESTATION_ASSERTION.inst();
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttestationAssertion.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static AttestationAssertion of(@NonNull ByteBuffer value) {
        return AttestationAssertion.builder().value(value).build();
    }

    public static AttestationAssertion of(byte[] value) {
        return of(ByteBuffer.wrap(value));
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