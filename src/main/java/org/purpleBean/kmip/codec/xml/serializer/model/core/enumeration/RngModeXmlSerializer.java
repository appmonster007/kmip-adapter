package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

public class RngModeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RngMode, String> {

    public RngModeXmlSerializer() {
        super(RngMode::getDescription);
    }
}