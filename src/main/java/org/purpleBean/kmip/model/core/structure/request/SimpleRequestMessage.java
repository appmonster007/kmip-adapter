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
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SimpleRequestMessage implements RequestMessageStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.REQUEST_MESSAGE.inst();

    static {
        for (KmipSpec spec : KmipSpec.values()) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SimpleRequestMessage.class);
        }
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