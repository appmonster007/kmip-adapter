package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.InitialDate;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.time.OffsetDateTime;

public class InitialDateXmlSerializer extends AbstractKmipXmlSerializer<InitialDate, OffsetDateTime> {

    public InitialDateXmlSerializer() {
        super(InitialDate::getValue);
    }
}