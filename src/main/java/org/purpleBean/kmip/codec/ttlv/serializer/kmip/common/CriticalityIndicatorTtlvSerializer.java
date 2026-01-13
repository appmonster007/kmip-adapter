package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorTtlvSerializer() {
        super(CriticalityIndicator::getValue);
    }
}