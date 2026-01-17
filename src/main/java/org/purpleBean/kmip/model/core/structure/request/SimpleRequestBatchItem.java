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

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestBatchItem.class);
        RequestBatchItemStructure.register(KmipSpec.UnknownVersion, encodingType, SimpleRequestBatchItem.class, SimpleRequestBatchItem::of);
    }

    private SimpleRequestBatchItem() {
        validate();
    }

    public static SimpleRequestBatchItem of(KmipDataType... values) {
        return of(List.of(values));
    }

    public static SimpleRequestBatchItem of(List<KmipDataType> kmipDataTypes) {
        return new SimpleRequestBatchItem();
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