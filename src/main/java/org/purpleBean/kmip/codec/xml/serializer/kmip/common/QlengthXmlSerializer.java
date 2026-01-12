package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class QlengthXmlSerializer extends AbstractKmipXmlSerializer<Qlength, Integer> {

    public QlengthXmlSerializer() {
        super(Qlength::getValue);
    }
}