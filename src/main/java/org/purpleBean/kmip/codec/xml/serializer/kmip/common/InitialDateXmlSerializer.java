package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InitialDate, OffsetDateTime> {

    public InitialDateXmlSerializer() {
        super(InitialDate::getValue);
    }
}