package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.BatchCount;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class BatchCountXmlSerializer extends AbstractKmipXmlSerializer<BatchCount, Integer> {

    public BatchCountXmlSerializer() {
        super(BatchCount::getValue);
    }
}