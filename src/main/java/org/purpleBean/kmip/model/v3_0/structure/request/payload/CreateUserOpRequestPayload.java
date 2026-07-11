package org.purpleBean.kmip.model.v3_0.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.Attributes;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class CreateUserOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.CREATE_USER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CreateUserOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, CreateUserOpRequestPayload.class, CreateUserOpRequestPayload::of);
        }
    }

    @NonNull
    private final Attributes attributes;

    @Builder
    private CreateUserOpRequestPayload(@NonNull Attributes attributes) {
        this.attributes = attributes;
        validate();
    }

    public static CreateUserOpRequestPayload of(List<KmipDataType> values) {
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return CreateUserOpRequestPayload.builder()
                .attributes((Attributes) map.get(Attributes.kmipTag).getFirst())
                .build();
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
        return new KmipDataType[]{ attributes };
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
