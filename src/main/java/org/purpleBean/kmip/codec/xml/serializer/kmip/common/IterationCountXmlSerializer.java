package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IterationCount, Integer> {

    public IterationCountXmlSerializer() {
        super(IterationCount::getValue);
    }
}