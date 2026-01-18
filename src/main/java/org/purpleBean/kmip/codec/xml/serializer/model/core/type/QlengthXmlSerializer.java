package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Qlength;

public class QlengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Qlength, Integer> {

    public QlengthXmlSerializer() {
        super(Qlength::getValue);
    }
}