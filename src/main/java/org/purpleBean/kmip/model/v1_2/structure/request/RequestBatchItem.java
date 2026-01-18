package org.purpleBean.kmip.model.v1_2.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayload;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class RequestBatchItem implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.BATCH_ITEM.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestBatchItem.class);
        }
    }

    @NonNull
    private final Operation operation;

    private final UniqueBatchItemID uniqueBatchItemID;

    @NonNull
    private final RequestPayload requestPayload;

    private final MessageExtension messageExtension;

    @Builder
    private RequestBatchItem(
            @NonNull Operation operation,
            UniqueBatchItemID uniqueBatchItemID,
            @NonNull RequestPayload requestPayload,
            MessageExtension messageExtension
    ) {
        this.operation = operation;
        this.uniqueBatchItemID = uniqueBatchItemID;
        this.requestPayload = requestPayload;
        this.messageExtension = messageExtension;
        validate();
    }

    private void validate() {
        Objects.requireNonNull(operation, "Operation cannot be null");
        Objects.requireNonNull(requestPayload, "RequestPayload cannot be null");
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
        return Stream.of(
                        operation,
                        uniqueBatchItemID,
                        requestPayload,
                        messageExtension)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}