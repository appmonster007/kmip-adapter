package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthXmlSerializer extends AbstractKmipXmlSerializer<DataLength, Integer> {

    public DataLengthXmlSerializer() {
        super(DataLength::getValue);
    }
}