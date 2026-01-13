package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QString XML Serialization Tests")
class QStringXmlTest extends AbstractXmlSerializationTestSuite<QString> {

    @Override
    protected Class<QString> type() {
        return QString.class;
    }

    @Override
    protected QString createDefault() {
        return QString.of("test-qstring".getBytes());
    }

    @Override
    protected QString createVariant() {
        return QString.of("another-qstring".getBytes());
    }
}