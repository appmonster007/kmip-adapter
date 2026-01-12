package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.RngMode;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class RngModeXmlSerializer extends AbstractKmipXmlSerializer<RngMode, String> {

    public RngModeXmlSerializer() {
        super(RngMode::getDescription);
    }
}