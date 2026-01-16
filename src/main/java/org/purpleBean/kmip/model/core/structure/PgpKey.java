package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class PgpKey implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PGP_KEY.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PgpKey.class);
        }
    }

    @NonNull
    private final PgpKeyVersion pgpKeyVersion;

    @NonNull
    private final KeyBlock keyBlock;

    @Builder
    private PgpKey(
            @NonNull PgpKeyVersion pgpKeyVersion,
            @NonNull KeyBlock keyBlock
    ) {
        this.pgpKeyVersion = pgpKeyVersion;
        this.keyBlock = keyBlock;
        validate();
    }

    public static PgpKey of(
            @NonNull PgpKeyVersion pgpKeyVersion,
            @NonNull KeyBlock keyBlock
    ) {
        return PgpKey.builder()
                .pgpKeyVersion(pgpKeyVersion)
                .keyBlock(keyBlock)
                .build();
    }

    private void validate() {
        Objects.requireNonNull(pgpKeyVersion, "PgpKeyVersion cannot be null");
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
        return List.of(pgpKeyVersion, keyBlock);
    }
}