package org.purpleBean.kmip.common.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class SimpleRequestMessage implements RequestMessageStructure {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;

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
        KmipSpec spec = KmipContext.getSpec();
        return true;
    }
}
