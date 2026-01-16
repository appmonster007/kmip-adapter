package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class PrivateKey implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PrivateKey.class);
        }
    }

    @NonNull
    private final KeyBlock keyBlock;

    @Builder
    private PrivateKey(
            @NonNull KeyBlock keyBlock
    ) {
        this.keyBlock = keyBlock;
        validate();
    }

    public static PrivateKey of(
            @NonNull KeyBlock keyBlock
    ) {
        return PrivateKey.builder()
                .keyBlock(keyBlock)
                .build();
    }

    private void validate() {
        Objects.requireNonNull(keyBlock, "KeyBlock cannot be null");
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
        return List.of(keyBlock);
    }
}