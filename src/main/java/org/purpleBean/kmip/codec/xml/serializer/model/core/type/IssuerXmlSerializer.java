package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Issuer;

public class IssuerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Issuer, String> {

    public IssuerXmlSerializer() {
        super(Issuer::getValue);
    }
}