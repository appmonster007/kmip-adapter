package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.api.request.RequestMessageStructure;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.purpleBean.kmip.api.KmipTag.Standard.BATCH_ITEM;
import static org.purpleBean.kmip.api.KmipTag.Standard.REQUEST_HEADER;

@Data
@Builder(toBuilder = true)
public class SimpleRequestMessage implements RequestMessageStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestMessage.class);
        RequestMessageStructure.register(KmipSpec.UnknownVersion, SimpleRequestMessage.class, SimpleRequestMessage::of);
    }

    @NonNull
    private final RequestHeaderStructure requestHeader;
    @NonNull
    @Singular
    private final List<RequestBatchItemStructure> requestBatchItems;
    @NonNull
    @Singular
    private final List<Exception> requestBatchItemErrors;

    @Builder
    private SimpleRequestMessage(
            @NonNull RequestHeaderStructure requestHeader,
            List<RequestBatchItemStructure> requestBatchItems,
            List<Exception> requestBatchItemErrors
    ) {
        this.requestHeader = requestHeader;
        this.requestBatchItems = (requestBatchItems == null) ? Collections.emptyList() : requestBatchItems;
        this.requestBatchItemErrors = (requestBatchItemErrors == null) ? Collections.emptyList() : requestBatchItemErrors;
        validate();
    }

    public static SimpleRequestMessage of(KmipDataType... values) {
        return of(List.of(values), List.of());
    }

    public static SimpleRequestMessage of(List<KmipDataType> values, List<Exception> errors) {
        var builder = SimpleRequestMessage.builder();
        builder.requestBatchItemErrors(errors);
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(REQUEST_HEADER.inst())) {
            builder.requestHeader((SimpleRequestHeader) map.get(REQUEST_HEADER.inst()).get(0));
        }
        if (map.containsKey(BATCH_ITEM.inst())) {
            builder.requestBatchItems(
                    map.get(BATCH_ITEM.inst()).stream()
                            .map(e -> (SimpleRequestBatchItem) e)
                            .collect(Collectors.toList())
            );
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // Add validation logic here
        if (requestBatchItems.size() != requestBatchItemErrors.size()) {
            throw new IllegalArgumentException("requestBatchItems and requestBatchItemErrors must have the same size");
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
    public KmipDataType[] getValue() {
        return Stream.of(requestHeader, requestBatchItems)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public boolean isSupported() {
        return supportedVersions.contains(KmipContext.getSpec());
    }
}
