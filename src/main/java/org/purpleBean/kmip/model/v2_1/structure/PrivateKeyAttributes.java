package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class PrivateKeyAttributes implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY_ATTRIBUTES.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PrivateKeyAttributes.class);
        }
    }

    @NonNull
    @Singular
    private final List<KmipAttribute> attributes;

    @Builder
    private PrivateKeyAttributes(List<KmipAttribute> attributes) {
        this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
        validate();
    }

    public static PrivateKeyAttributes of(List<KmipAttribute> attributes) {
        return PrivateKeyAttributes.builder().attributes(attributes).build();
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
        return Stream.of(attributes)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
