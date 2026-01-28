package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class OpaqueObject implements ManagedObject, KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.OPAQUE_OBJECT.inst();
    public static final ObjectType.Value objectTypeValue = ObjectType.Standard.OPAQUE_OBJECT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, OpaqueObject.class);
            ManagedObject.register(spec, kmipTag.getValue(), encodingType, objectTypeValue, OpaqueObject.class, OpaqueObject::of);
        }
    }

    @NonNull
    private final OpaqueDataType opaqueDataType;

    @NonNull
    private final OpaqueDataValue opaqueDataValue;

    @Builder
    private OpaqueObject(
            @NonNull OpaqueDataType opaqueDataType,
            @NonNull OpaqueDataValue opaqueDataValue
    ) {
        this.opaqueDataType = opaqueDataType;
        this.opaqueDataValue = opaqueDataValue;
        validate();
    }

    public static OpaqueObject of(
            @NonNull OpaqueDataType opaqueDataType,
            @NonNull OpaqueDataValue opaqueDataValue
    ) {
        return OpaqueObject.builder()
                .opaqueDataType(opaqueDataType)
                .opaqueDataValue(opaqueDataValue)
                .build();
    }

    public static OpaqueObject of(List<KmipDataType> values) {
        var builder = OpaqueObject.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(OpaqueDataType.kmipTag)) {
            builder.opaqueDataType((OpaqueDataType) map.get(OpaqueDataType.kmipTag).getFirst());
        }
        if (map.containsKey(OpaqueDataValue.kmipTag)) {
            builder.opaqueDataValue((OpaqueDataValue) map.get(OpaqueDataValue.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(opaqueDataType, "OpaqueDataType cannot be null");
        Objects.requireNonNull(opaqueDataValue, "OpaqueDataValue cannot be null");
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
        return supportedVersions.contains(spec) && getValue().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValue() {
        return List.of(opaqueDataType, opaqueDataValue);
    }

    @Override
    public ObjectType getObjectType() {
        return objectTypeValue.inst();
    }
}
