package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class ProtectionStorageMasks implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PROTECTION_STORAGE_MASKS.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtectionStorageMasks.class);
        }
    }

    @NonNull
    @Singular("protectionStorageMask")
    private final List<ProtectionStorageMask> protectionStorageMasks;

    @Builder
    private ProtectionStorageMasks(List<ProtectionStorageMask> protectionStorageMasks) {
        this.protectionStorageMasks = (protectionStorageMasks == null) ? Collections.emptyList() : protectionStorageMasks;
        validate();
    }

    public static ProtectionStorageMasks of(@NonNull List<ProtectionStorageMask> masks) {
        return ProtectionStorageMasks.builder().protectionStorageMasks(masks).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && protectionStorageMasks.stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return protectionStorageMasks.stream().map(KmipDataType.class::cast).toArray(KmipDataType[]::new);
    }
}
