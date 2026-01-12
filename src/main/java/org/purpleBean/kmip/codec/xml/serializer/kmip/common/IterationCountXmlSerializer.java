package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.IterationCount;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class IterationCountXmlSerializer extends AbstractKmipXmlSerializer<IterationCount, Integer> {

    public IterationCountXmlSerializer() {
        super(IterationCount::getValue);
    }
}