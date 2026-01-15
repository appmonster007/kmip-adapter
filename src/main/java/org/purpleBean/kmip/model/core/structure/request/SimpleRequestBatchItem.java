package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class SimpleRequestBatchItem implements RequestBatchItemStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.BATCH_ITEM.inst();

    static {
        for (KmipSpec spec : KmipSpec.values()) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SimpleRequestBatchItem.class);
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
    public List<KmipDataType> getValues() {
        return List.of();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return true;
    }
}