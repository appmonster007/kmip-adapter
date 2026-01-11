package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorXmlDeserializer extends AbstractKmipXmlDeserializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorXmlDeserializer() {
        super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType, Boolean.class, value -> CriticalityIndicator.builder().value(value).build());
    }
}