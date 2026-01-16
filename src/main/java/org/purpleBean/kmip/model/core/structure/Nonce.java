package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Nonce implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.NONCE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Nonce.class);
        }
    }

    @NonNull
    private final NonceId nonceId;
    @NonNull
    private final NonceValue nonceValue;

    @Builder
    private Nonce(
            @NonNull NonceId nonceId,
            @NonNull NonceValue nonceValue

    ) {
        this.nonceId = nonceId;
        this.nonceValue = nonceValue;
        validate();
    }

    public static Nonce of(
            @NonNull NonceId nonceId,
            @NonNull NonceValue nonceValue

    ) {
        return Nonce.builder()
                .nonceId(nonceId)
                .nonceValue(nonceValue)
                .build();
    }

    private void validate() {
        // Add validation logic here
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return List.of(nonceId, nonceValue);
    }
}