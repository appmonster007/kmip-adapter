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
public class TemplateAttribute implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.TEMPLATE_ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, TemplateAttribute.class);
        }
    }

    @NonNull
    @Singular
    private final List<Name> names;
    @NonNull
    @Singular
    private final List<Attribute> attributes;

    @Builder
    private TemplateAttribute(
            List<Name> names,
            List<Attribute> attributes

    ) {
        this.names = (names == null) ? Collections.emptyList() : names;
        this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
        validate();
    }

    public static TemplateAttribute of(
            List<Name> names,
            List<Attribute> attributes

    ) {
        return TemplateAttribute.builder()
                .names(names)
                .attributes(attributes)
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // Add validation logic here
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
        return Stream.concat(
                        names.stream().map(KmipDataType.class::cast),
                        attributes.stream().map(KmipDataType.class::cast)
                )
                .filter(Objects::nonNull)
                .toList();
    }
}