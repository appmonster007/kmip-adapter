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
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DefaultsInformation implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.DEFAULTS_INFORMATION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DefaultsInformation.class);
        }
    }

    @NonNull
    @Singular
    private final List<ObjectDefaults> objectDefaults;

    @Builder
    private DefaultsInformation(List<ObjectDefaults> objectDefaults) {
        this.objectDefaults = (objectDefaults == null) ? Collections.emptyList() : Collections.unmodifiableList(objectDefaults);
        validate();
    }

    public static DefaultsInformation of(List<ObjectDefaults> objectDefaults) {
        return DefaultsInformation.builder().objectDefaults(objectDefaults).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        if (objectDefaults == null || objectDefaults.isEmpty()) {
            throw new IllegalArgumentException("DefaultsInformation requires at least one ObjectDefaults entry");
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
        return Stream.of(objectDefaults)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
