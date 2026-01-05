package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.Set;

/**
 * KMIP BatchOrderOption dataType.
 */
@Data
@Builder(toBuilder = true)
public class BatchOrderOption implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.BATCH_ORDER_OPTION);
    public static final EncodingType encodingType = EncodingType.BOOLEAN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, BatchOrderOption.class);
        }
    }


    @NonNull
    private final Boolean value;

    public static BatchOrderOption of(@NonNull Boolean value) {
        return BatchOrderOption.builder().value(value).build();
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