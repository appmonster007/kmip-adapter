package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CriticalityIndicator;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CriticalityIndicatorXmlSerializer extends AbstractKmipXmlSerializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorXmlSerializer() {
        super(CriticalityIndicator::getValue);
    }
}