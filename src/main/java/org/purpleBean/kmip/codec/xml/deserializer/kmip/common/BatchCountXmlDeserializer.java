package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountXmlDeserializer extends AbstractKmipXmlDeserializer<BatchCount, Integer> {

    public BatchCountXmlDeserializer() {
        super(BatchCount.kmipTag, BatchCount.encodingType, Integer.class, value -> BatchCount.builder().value(value).build());
    }
}