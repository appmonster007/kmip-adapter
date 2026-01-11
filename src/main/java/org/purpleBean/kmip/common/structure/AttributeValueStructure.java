package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class AttributeValueStructure implements AttributeValue, KmipStructure {
    public static final KmipTag kmipTag = AttributeValue.kmipTag;
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValueStructure.class);
        }
    }

    @NonNull
    @Singular
    private final List<KmipDataType> values;

    public static AttributeValueStructure of(@NonNull List<KmipDataType> values) {
        return AttributeValueStructure.builder().values(values).build();
    }

    public static AttributeValueStructure of(@NonNull KmipDataType... values) {
        return of(List.of(values));
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
        return values.stream().filter(Objects::nonNull).toList();
    }
}
