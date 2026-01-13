package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<QString, ByteBuffer> {

    public QStringXmlSerializer() {
        super(QString::getValue);
    }
}