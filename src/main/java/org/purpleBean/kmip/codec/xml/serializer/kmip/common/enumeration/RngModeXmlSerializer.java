package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RngMode, String> {

    public RngModeXmlSerializer() {
        super(RngMode::getDescription);
    }
}