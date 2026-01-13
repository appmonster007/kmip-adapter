package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Modulus, BigInteger> {

    public ModulusXmlSerializer() {
        super(Modulus::getValue);
    }
}