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
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class CommonTemplateAttribute implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.COMMON_TEMPLATE_ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CommonTemplateAttribute.class);
        }
    }

    @NonNull
    @Singular
    private final List<Name> names;

    @NonNull
    @Singular
    private final List<Attribute> attributes;

    @Builder
    private CommonTemplateAttribute(List<Name> names, List<Attribute> attributes) {
        this.names = (names == null) ? Collections.emptyList() : names;
        this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
        validate();
    }

    public static CommonTemplateAttribute of(List<Name> names, List<Attribute> attributes) {
        return CommonTemplateAttribute.builder().names(names).attributes(attributes).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(names, "Names cannot be null");
        Objects.requireNonNull(attributes, "Attributes cannot be null");
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
        return Stream.concat(names.stream(), attributes.stream())
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
