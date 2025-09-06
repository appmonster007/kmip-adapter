package org.purpleBean.kmip.common.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SimpleRequestMessage implements RequestMessageStructure {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE);
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @NonNull
    private final SimpleRequestHeader requestHeader;
    @NonNull
    @Singular
    private final List<SimpleRequestBatchItem> requestBatchItems;
    @NonNull
    @Singular
    private final List<Exception> requestBatchItemErrors;


    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(requestHeader);
        values.addAll(requestBatchItems);
        return values;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return true;
    }
}
