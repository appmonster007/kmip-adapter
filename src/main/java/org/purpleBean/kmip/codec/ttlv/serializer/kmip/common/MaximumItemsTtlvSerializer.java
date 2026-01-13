package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MaximumItems, Integer> {

    public MaximumItemsTtlvSerializer() {
        super(MaximumItems::getValue);
    }
}