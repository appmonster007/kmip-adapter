package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorJsonSerializer() {
        super(CriticalityIndicator::getValue);
    }
}