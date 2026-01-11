package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountXmlDeserializer extends AbstractKmipXmlDeserializer<IterationCount, Integer> {

    public IterationCountXmlDeserializer() {
        super(IterationCount.kmipTag, IterationCount.encodingType, Integer.class, value -> IterationCount.builder().value(value).build());
    }
}