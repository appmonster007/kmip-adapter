package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DataLength, Integer> {

    public DataLengthXmlSerializer() {
        super(DataLength::getValue);
    }
}