package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.SplitKeyThreshold;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class SplitKeyThresholdXmlSerializer extends AbstractKmipXmlSerializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdXmlSerializer() {
        super(SplitKeyThreshold::getValue);
    }
}