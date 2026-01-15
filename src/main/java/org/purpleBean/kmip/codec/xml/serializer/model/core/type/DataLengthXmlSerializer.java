package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DataLength;

public class DataLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DataLength, Integer> {

    public DataLengthXmlSerializer() {
        super(DataLength::getValue);
    }
}