package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class PgpKey implements ManagedObject, KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PGP_KEY.inst();
    public static final ObjectType.Value objectTypeValue = ObjectType.Standard.PGP_KEY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PgpKey.class);
            ManagedObject.register(spec, kmipTag.getValue(), encodingType, objectTypeValue, PgpKey.class, PgpKey::of);
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

    public static PgpKey of(List<KmipDataType> values) {
        var builder = PgpKey.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(PgpKeyVersion.kmipTag)) {
            builder.pgpKeyVersion((PgpKeyVersion) map.get(PgpKeyVersion.kmipTag).getFirst());
        }
        if (map.containsKey(KeyBlock.kmipTag)) {
            builder.keyBlock((KeyBlock) map.get(KeyBlock.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
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

    @Override
    public ObjectType getObjectType() {
        return objectTypeValue.inst();
    }
}
