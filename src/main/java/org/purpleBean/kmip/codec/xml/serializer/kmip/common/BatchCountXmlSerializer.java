package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BatchCount, Integer> {

    public BatchCountXmlSerializer() {
        super(BatchCount::getValue);
    }
}