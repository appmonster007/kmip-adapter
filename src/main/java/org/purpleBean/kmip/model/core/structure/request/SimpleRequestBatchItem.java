package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SimpleRequestBatchItem implements RequestBatchItemStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestBatchItem.class);
        RequestBatchItemStructure.register(KmipSpec.UnknownVersion, SimpleRequestBatchItem.class, SimpleRequestBatchItem::of);
    }

    //    @NonNull
    private final Operation operation;

    @NonNull
    private final RequestPayloadStructure requestPayloadStructure;

    private SimpleRequestBatchItem(
//            @NonNull
            Operation operation,
            @NonNull
            RequestPayloadStructure requestPayloadStructure
    ) {
        this.operation = operation;
        this.requestPayloadStructure = requestPayloadStructure;
        validate();
    }

    public static SimpleRequestBatchItem of(KmipDataType... values) {
        return of(List.of(values));
    }

    public static SimpleRequestBatchItem of(List<KmipDataType> values) {
        var builder = SimpleRequestBatchItem.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(Operation.kmipTag)) {
            builder.operation((Operation) map.get(Operation.kmipTag).get(0));
        }
        if (map.containsKey(RequestPayloadStructure.kmipTag)) {
            builder.requestPayloadStructure((RequestPayloadStructure) map.get(RequestPayloadStructure.kmipTag).get(0));
        }
        return builder.build();
    }

    @Override
    public RequestPayloadStructure getRequestPayload() {
        return requestPayloadStructure;
    }

    private void validate() {
        // No validation needed for this structure
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
    public List<KmipDataType> getValues() {
        return Stream.of(operation, requestPayloadStructure).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        return supportedVersions.contains(KmipContext.getSpec());
    }
}