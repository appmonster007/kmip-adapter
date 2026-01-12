package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class DXmlSerializer extends AbstractKmipXmlSerializer<D, BigInteger> {

    public DXmlSerializer() {
        super(D::getValue);
    }
}