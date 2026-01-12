package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsTtlvSerializer extends AbstractKmipTtlvSerializer<MaximumItems, Integer> {

    public MaximumItemsTtlvSerializer() {
        super(MaximumItems::getValue);
    }
}