package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.BatchCount;

public class BatchCountXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<BatchCount, Integer> {

    public BatchCountXmlDeserializer() {
        super(BatchCount.kmipTag, BatchCount.encodingType, Integer.class, value -> BatchCount.builder().value(value).build());
    }
}