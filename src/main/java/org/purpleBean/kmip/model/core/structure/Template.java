package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;

import java.util.*;

@Data
@Builder(toBuilder = true)
public class Template implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.TEMPLATE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Template.class);
        }
    }

    @NonNull
    @Singular
    private final List<Attribute> attributes;

    @Builder
    private Template(List<Attribute> attributes) {
        this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
        validate();
    }

    public static Template of(List<Attribute> attributes) {
        return Template.builder().attributes(attributes).build();
    }

    private void validate() {
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
        return new ArrayList<>(attributes);
    }
}