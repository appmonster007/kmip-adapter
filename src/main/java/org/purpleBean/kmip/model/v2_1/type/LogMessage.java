package org.purpleBean.kmip.model.v2_1.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP LogMessage dataType — TextString carrying the log message for the Log operation.
 * Tag: LOG_MESSAGE (0x420141), supported V2_1 and V3_0.
 */
@Data
@Builder(toBuilder = true)
public class LogMessage implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.LOG_MESSAGE.inst();
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LogMessage.class);
        }
    }

    @NonNull
    private final String value;

    @Builder
    private LogMessage(@NonNull String value) {
        this.value = value;
        validate();
    }

    public static LogMessage of(@NonNull String value) {
        return new LogMessage(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
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
