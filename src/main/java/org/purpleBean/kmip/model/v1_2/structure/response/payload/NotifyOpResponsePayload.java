package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class NotifyOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.NOTIFY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, NotifyOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, NotifyOpResponsePayload.class, NotifyOpResponsePayload::of);
        }
    }

    @Builder
    private NotifyOpResponsePayload() {
        validate();
    }

    public static NotifyOpResponsePayload of(List<KmipDataType> values) {
        return NotifyOpResponsePayload.builder().build();
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
        return supportedVersions.contains(spec);
    }

    @Override
    public List<KmipDataType> getValue() {
        return List.of();
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
