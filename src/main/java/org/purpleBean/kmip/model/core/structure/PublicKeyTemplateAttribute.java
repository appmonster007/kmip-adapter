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
public class PublicKeyTemplateAttribute implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PublicKeyTemplateAttribute.class);
        }
    }

    @NonNull
    @Singular
    private final List<Name> names;

    @NonNull
    @Singular
    private final List<Attribute> attributes;

    @Builder
    private PublicKeyTemplateAttribute(List<Name> names, List<Attribute> attributes) {
        this.names = (names == null) ? Collections.emptyList() : names;
        this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
        validate();
    }

    public static PublicKeyTemplateAttribute of(List<Name> names, List<Attribute> attributes) {
        return PublicKeyTemplateAttribute.builder().names(names).attributes(attributes).build();
    }

    private void validate() {
        isSupported();
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.concat(names.stream().map(KmipDataType.class::cast), attributes.stream().map(KmipDataType.class::cast))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}