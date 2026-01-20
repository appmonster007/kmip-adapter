package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class SimpleRequestPayload implements RequestPayloadStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestPayload.class);
        RequestPayloadStructure.register(KmipSpec.UnknownVersion, null, SimpleRequestPayload.class, SimpleRequestPayload::of);
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
        isSupported();
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
        return supportedVersions.contains(KmipContext.getSpec());
    }

    @Override
    public List<KmipDataType> getValues() {
        return List.of();
    }

    @Override
    public Operation getCorrespondingOperation() {
        return null;
    }
}