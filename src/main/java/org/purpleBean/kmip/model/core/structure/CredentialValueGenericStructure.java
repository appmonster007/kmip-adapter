package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class CredentialValueGenericStructure implements CredentialValue, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CredentialValueGenericStructure.class);
            CredentialValue.register(spec, encodingType, null, CredentialValueGenericStructure.class, CredentialValueGenericStructure::of);
        }
    }

    @NonNull
    @Singular
    private final List<KmipDataType> values;

    @Builder
    private CredentialValueGenericStructure(
            List<KmipDataType> values
    ) {
        this.values = (values == null) ? Collections.emptyList() : values;
        validate();
    }

    public static CredentialValueGenericStructure of(KmipDataType... values) {
        return of(List.of(values));
    }

    public static CredentialValueGenericStructure of(
            List<KmipDataType> values
    ) {
        return CredentialValueGenericStructure.builder()
                .values(values)
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(values, "Values cannot be null");
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
        return values.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}