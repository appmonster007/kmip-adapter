package org.purpleBean.kmip.model.core.structure.response;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SimpleResponsePayload implements ResponsePayloadStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleResponsePayload.class);
        ResponsePayloadStructure.register(KmipSpec.UnknownVersion, null, SimpleResponsePayload.class, SimpleResponsePayload::of);
    }

    @Builder
    private SimpleResponsePayload() {
        validate();
    }

    public static SimpleResponsePayload of() {
        return SimpleResponsePayload.builder().build();
    }

    public static SimpleResponsePayload of(List<KmipDataType> values) {
        return SimpleResponsePayload.builder().build();
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
        return supportedVersions.contains(KmipContext.getSpec());
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of()
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() {
        return null;
    }
}
