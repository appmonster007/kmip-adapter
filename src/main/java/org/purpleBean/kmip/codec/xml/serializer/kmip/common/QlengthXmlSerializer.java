package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Qlength, Integer> {

    public QlengthXmlSerializer() {
        super(Qlength::getValue);
    }
}