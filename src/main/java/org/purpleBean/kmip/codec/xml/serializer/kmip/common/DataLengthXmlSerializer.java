package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DataLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DataLengthXmlSerializer extends AbstractKmipXmlSerializer<DataLength, Integer> {

    public DataLengthXmlSerializer() {
        super(DataLength::getValue);
    }
}