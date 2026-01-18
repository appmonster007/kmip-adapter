package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayload;

import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class SimpleRequestPayload implements RequestPayload, KmipStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SimpleRequestPayload.class);
            RequestPayload.register(spec, null, SimpleRequestPayload.class, SimpleRequestPayload::of);
        }
    }

    @Builder
    private SimpleRequestPayload() {
        validate();
    }

    public static SimpleRequestPayload of() {
        return SimpleRequestPayload.builder().build();
    }

    public static SimpleRequestPayload of(List<KmipDataType> values) {
        return SimpleRequestPayload.builder().build();
    }

    private void validate() {
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
        return List.of();
    }
}