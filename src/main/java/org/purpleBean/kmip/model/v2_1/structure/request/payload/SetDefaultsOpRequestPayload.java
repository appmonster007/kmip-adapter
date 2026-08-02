package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.structure.DefaultsInformation;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SetDefaultsOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.SET_DEFAULTS;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SetDefaultsOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, SetDefaultsOpRequestPayload.class, SetDefaultsOpRequestPayload::of);
        }
    }

    private final DefaultsInformation defaultsInformation;

    @Builder
    private SetDefaultsOpRequestPayload(DefaultsInformation defaultsInformation) {
        this.defaultsInformation = defaultsInformation;
        validate();
    }

    public static SetDefaultsOpRequestPayload of(List<KmipDataType> values) {
        var builder = SetDefaultsOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(DefaultsInformation.kmipTag)) {
            builder.defaultsInformation((DefaultsInformation) map.get(DefaultsInformation.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return defaultsInformation == null ? new KmipDataType[0] : new KmipDataType[]{defaultsInformation};
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
