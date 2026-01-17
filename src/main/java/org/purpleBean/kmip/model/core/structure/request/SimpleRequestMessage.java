package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestMessageStructure;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.purpleBean.kmip.api.KmipTag.Standard.BATCH_ITEM;
import static org.purpleBean.kmip.api.KmipTag.Standard.REQUEST_HEADER;

@Data
@Builder(toBuilder = true)
public class SimpleRequestMessage implements RequestMessageStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.REQUEST_MESSAGE.inst();

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestMessage.class);
        RequestMessageStructure.register(KmipSpec.UnknownVersion, encodingType, SimpleRequestMessage.class, SimpleRequestMessage::of);
    }

    @NonNull
    private final SimpleRequestHeader requestHeader;
    @NonNull
    @Singular
    private final List<SimpleRequestBatchItem> requestBatchItems;
    @NonNull
    @Singular
    private final List<Exception> requestBatchItemErrors;

    @Builder
    private SimpleRequestMessage(
            @NonNull SimpleRequestHeader requestHeader,
            List<SimpleRequestBatchItem> requestBatchItems,
            List<Exception> requestBatchItemErrors
    ) {
        this.requestHeader = requestHeader;
        this.requestBatchItems = (requestBatchItems == null) ? Collections.emptyList() : requestBatchItems;
        this.requestBatchItemErrors = (requestBatchItemErrors == null) ? Collections.emptyList() : requestBatchItemErrors;
        validate();
    }

    public static SimpleRequestMessage of(KmipDataType... values) {
        return of(List.of(values));
    }

    public static SimpleRequestMessage of(List<KmipDataType> values) {
        var builder = SimpleRequestMessage.builder();
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
        return Stream.concat(Stream.of(requestHeader), requestBatchItems.stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}