package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ResultMessage;

public class ResultMessageXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ResultMessage, String> {

    public ResultMessageXmlSerializer() {
        super(ResultMessage::getValue);
    }
}