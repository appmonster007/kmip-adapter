package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<QString, ByteBuffer> {

    public QStringXmlDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}