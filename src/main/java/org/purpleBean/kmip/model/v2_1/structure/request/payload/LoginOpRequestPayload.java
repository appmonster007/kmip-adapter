package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class LoginOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.LOGIN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LoginOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, LoginOpRequestPayload.class, LoginOpRequestPayload::of);
        }
    }

    @NonNull
    private final Credential credential;

    @Builder
    private LoginOpRequestPayload(@NonNull Credential credential) {
        this.credential = credential;
        validate();
    }

    public static LoginOpRequestPayload of(List<KmipDataType> values) {
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return LoginOpRequestPayload.builder()
                .credential((Credential) map.get(Credential.kmipTag).getFirst())
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
    public boolean isSupported() { return supportedVersions.contains(KmipContext.getSpec()); }

    @Override
    public KmipDataType[] getValue() {
        return new KmipDataType[]{credential};
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
