package org.purpleBean.kmip.common.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.List;

@Data
@Builder
public class SimpleRequestBatchItem implements RequestBatchItemStructure {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.BATCH_ITEM);
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public List<KmipDataType> getValues() {
        return List.of();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return true;
    }
}