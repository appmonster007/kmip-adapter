package org.purpleBean.kmip.model.core.structure.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.purpleBean.kmip.api.KmipTag.Standard.BATCH_ITEM;
import static org.purpleBean.kmip.api.KmipTag.Standard.RESPONSE_HEADER;

@Data
@Builder(toBuilder = true)
public class SimpleResponseMessage implements ResponseMessageStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleResponseMessage.class);
        ResponseMessageStructure.register(KmipSpec.UnknownVersion, SimpleResponseMessage.class, SimpleResponseMessage::of);
    }

    @NonNull
    private final ResponseHeaderStructure responseHeader;
    @NonNull
    @Singular
    private final List<ResponseBatchItemStructure> responseBatchItems;
    @NonNull
    @Singular
    private final List<Exception> responseBatchItemErrors;

    @Builder
    private SimpleResponseMessage(
            @NonNull ResponseHeaderStructure responseHeader,
            List<ResponseBatchItemStructure> responseBatchItems,
            List<Exception> responseBatchItemErrors
    ) {
        this.responseHeader = responseHeader;
        this.responseBatchItems = (responseBatchItems == null) ? Collections.emptyList() : responseBatchItems;
        this.responseBatchItemErrors = (responseBatchItemErrors == null) ? Collections.emptyList() : responseBatchItemErrors;
        validate();
    }

    public static SimpleResponseMessage of(KmipDataType... values) {
        return of(List.of(values), List.of());
    }

    public static SimpleResponseMessage of(List<KmipDataType> values, List<Exception> errors) {
        var builder = SimpleResponseMessage.builder();
        builder.responseBatchItemErrors(errors);
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(RESPONSE_HEADER.inst())) {
            builder.responseHeader((SimpleResponseHeader) map.get(RESPONSE_HEADER.inst()).get(0));
        }
        if (map.containsKey(BATCH_ITEM.inst())) {
            builder.responseBatchItems(
                    map.get(BATCH_ITEM.inst()).stream()
                            .map(e -> (SimpleResponseBatchItem) e)
                            .collect(Collectors.toList())
            );
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        if (responseBatchItems.size() != responseBatchItemErrors.size()) {
            throw new IllegalArgumentException("responseBatchItems and responseBatchItemErrors must have the same size");
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
    public List<KmipDataType> getValue() {
        return Stream.concat(Stream.of(responseHeader), responseBatchItems.stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        return supportedVersions.contains(KmipContext.getSpec());
    }
}
