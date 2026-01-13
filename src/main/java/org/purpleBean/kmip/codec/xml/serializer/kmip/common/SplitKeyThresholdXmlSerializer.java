package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdXmlSerializer() {
        super(SplitKeyThreshold::getValue);
    }
}