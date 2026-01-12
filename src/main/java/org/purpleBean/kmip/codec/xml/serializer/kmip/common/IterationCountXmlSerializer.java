package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountXmlSerializer extends AbstractKmipXmlSerializer<IterationCount, Integer> {

    public IterationCountXmlSerializer() {
        super(IterationCount::getValue);
    }
}