package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

public class CriticalityIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorTtlvSerializer() {
        super(CriticalityIndicator::getValue);
    }
}