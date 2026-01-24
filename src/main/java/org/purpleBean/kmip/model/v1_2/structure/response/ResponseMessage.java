package org.purpleBean.kmip.model.v1_2.structure.response;

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

@Data
@Builder(toBuilder = true)
public class ResponseMessage implements ResponseMessageStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ResponseMessage.class);
            ResponseMessageStructure.register(spec, ResponseMessage.class, ResponseMessage::of);
        }
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
    private ResponseMessage(
            @NonNull ResponseHeaderStructure responseHeader,
            List<ResponseBatchItemStructure> responseBatchItems,
            List<Exception> responseBatchItemErrors
    ) {
        this.responseHeader = responseHeader;
        this.responseBatchItems = (responseBatchItems == null) ? Collections.emptyList() : responseBatchItems;
        this.responseBatchItemErrors = (responseBatchItemErrors == null) ? Collections.emptyList() : responseBatchItemErrors;
        validate();
    }

    public static ResponseMessage of(List<KmipDataType> values, List<Exception> errors) {
        var builder = ResponseMessage.builder();
        builder.responseBatchItemErrors(errors);
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ResponseHeaderStructure.kmipTag)) {
            builder.responseHeader((ResponseHeaderStructure) map.get(ResponseHeaderStructure.kmipTag).getFirst());
        }
        if (map.containsKey(ResponseBatchItemStructure.kmipTag)) {
            builder.responseBatchItems(
                    map.get(ResponseBatchItemStructure.kmipTag).stream()
                            .map(ResponseBatchItemStructure.class::cast)
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
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.concat(Stream.of(responseHeader), responseBatchItems.stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
