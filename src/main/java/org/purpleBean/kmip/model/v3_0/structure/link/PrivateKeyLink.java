package org.purpleBean.kmip.model.v3_0.structure.link;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class PrivateKeyLink implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY_LINK.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PrivateKeyLink.class);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    @Builder
    private PrivateKeyLink(@NonNull UniqueIdentifier uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
        validate();
    }

    public static PrivateKeyLink of(@NonNull UniqueIdentifier uniqueIdentifier) {
        return PrivateKeyLink.builder().uniqueIdentifier(uniqueIdentifier).build();
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(uniqueIdentifier)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
