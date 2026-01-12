package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorJsonSerializer extends AbstractKmipJsonSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorJsonSerializer() {
        super(CriticalityIndicator::getValue);
    }
}