package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountXmlSerializer extends AbstractKmipXmlSerializer<BatchCount, Integer> {

    public BatchCountXmlSerializer() {
        super(BatchCount::getValue);
    }
}