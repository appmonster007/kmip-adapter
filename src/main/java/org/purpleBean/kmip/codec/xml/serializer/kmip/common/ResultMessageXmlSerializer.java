package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ResultMessage, String> {

    public ResultMessageXmlSerializer() {
        super(ResultMessage::getValue);
    }
}