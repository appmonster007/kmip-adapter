package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

public class CriticalityIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorJsonSerializer() {
        super(CriticalityIndicator::getValue);
    }
}