package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorXmlSerializer() {
        super(CriticalityIndicator::getValue);
    }
}