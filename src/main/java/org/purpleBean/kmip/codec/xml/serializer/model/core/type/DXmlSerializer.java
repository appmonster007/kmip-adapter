package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.D;

import java.math.BigInteger;

public class DXmlSerializer extends AbstractKmipDataTypeXmlSerializer<D, BigInteger> {

    public DXmlSerializer() {
        super(D::getValue);
    }
}