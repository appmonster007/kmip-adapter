package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP Pkcs11OutputParameters dataType.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11OutputParameters implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS.inst();
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11OutputParameters.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    @Builder
    private Pkcs11OutputParameters(@NonNull ByteBuffer value) {
        this.value = value;
        validate();
    }

    public static Pkcs11OutputParameters of(@NonNull ByteBuffer value) {
        return new Pkcs11OutputParameters(value);
    }

    public static Pkcs11OutputParameters of(byte[] value) {
        return Pkcs11OutputParameters.builder().value(ByteBuffer.wrap(value)).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
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
