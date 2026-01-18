package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InitialDate, OffsetDateTime> {

    public InitialDateXmlSerializer() {
        super(InitialDate::getValue);
    }
}