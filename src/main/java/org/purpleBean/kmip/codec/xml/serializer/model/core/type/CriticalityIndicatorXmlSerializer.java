package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

public class CriticalityIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorXmlSerializer() {
        super(CriticalityIndicator::getValue);
    }
}