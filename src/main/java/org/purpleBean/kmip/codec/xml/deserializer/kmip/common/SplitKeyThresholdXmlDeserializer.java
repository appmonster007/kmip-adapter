package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdXmlDeserializer() {
        super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType, Integer.class, value -> SplitKeyThreshold.builder().value(value).build());
    }
}