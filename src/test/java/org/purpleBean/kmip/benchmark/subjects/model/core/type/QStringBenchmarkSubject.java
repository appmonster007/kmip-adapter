package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.QString;

public class QStringBenchmarkSubject extends KmipBenchmarkSubject<QString> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public QStringBenchmarkSubject() throws Exception {
        QString qString = QString.of("test-qstring".getBytes());
        initialize(qString, QString.class);
    }

    @Override
    public String name() {
        return "QString";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}