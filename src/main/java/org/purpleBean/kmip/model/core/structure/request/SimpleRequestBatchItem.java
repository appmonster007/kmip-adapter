package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
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

    private SimpleRequestBatchItem() {
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
        return List.of();
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}