package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestPayload;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SimpleRequestBatchItem implements RequestBatchItemStructure {

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestBatchItem.class);
        RequestBatchItemStructure.register(KmipSpec.UnknownVersion, SimpleRequestBatchItem.class, SimpleRequestBatchItem::of);
    }

    //    @NonNull
    private final Operation operation;

    @NonNull
    private final RequestPayload requestPayload;

    private SimpleRequestBatchItem(
//            @NonNull
            Operation operation,
            @NonNull
            RequestPayload requestPayload
    ) {
        this.operation = operation;
        this.requestPayload = requestPayload;
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
        if (map.containsKey(RequestPayload.kmipTag)) {
            builder.requestPayload((RequestPayload) map.get(RequestPayload.kmipTag).get(0));
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
        return Stream.of(operation, requestPayload).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}