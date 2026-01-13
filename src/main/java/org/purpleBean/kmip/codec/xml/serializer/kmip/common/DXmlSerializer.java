package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DXmlSerializer extends AbstractKmipDataTypeXmlSerializer<D, BigInteger> {

    public DXmlSerializer() {
        super(D::getValue);
    }
}