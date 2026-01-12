package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ResultMessage;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ResultMessageXmlSerializer extends AbstractKmipXmlSerializer<ResultMessage, String> {

    public ResultMessageXmlSerializer() {
        super(ResultMessage::getValue);
    }
}